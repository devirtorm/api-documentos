package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.entity.Cliente;
import com.sole.api_documentos.entity.ClientePublicoGeneral;
import com.sole.api_documentos.entity.ClientePublicoGeneralId;
import com.sole.api_documentos.entity.Usuario;
import com.sole.api_documentos.exception.ResourceNotFoundException;
import com.sole.api_documentos.mapper.ClientePublicoGeneralMapper;
import com.sole.api_documentos.repository.ClientePublicoGeneralRepository;
import com.sole.api_documentos.repository.ClienteRepository;
import com.sole.api_documentos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientePublicoGeneralServiceImpl implements ClientePublicoGeneralService {
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final ClientePublicoGeneralRepository clientePublicoGeneralRepository;
    private final ClientePublicoGeneralMapper clienteMapper;

    @Override
    @Transactional
    public List<ClientePublicoGeneralSummaryDTO> getAllMixedClientByAgente(String idUsuario, String idAgente) {

        // 1. Obtener configuración del usuario y cliente base
        Usuario configUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario web no encontrado"));

        Cliente clienteBase = clienteRepository.findById(configUsuario.getClienteModuloWeb())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente Base no encontrado"));

        // 2. Obtener y mapear los clientes de público general
        List<ClientePublicoGeneral> clientesGenerales = clientePublicoGeneralRepository.findByIdAgente(idAgente);
        List<ClientePublicoGeneralSummaryDTO> listaGenerales = clientesGenerales.stream()
                .map(clienteGeneral -> clienteMapper.fusionarCliente(clienteBase, clienteGeneral))
                .toList();

        // 3. Obtener y mapear los clientes normales
        List<Cliente> clientesNormales = clienteRepository.findByAgente(idAgente);
        List<ClientePublicoGeneralSummaryDTO> listaNormales = clientesNormales.stream()
                .map(clienteMapper::clienteToSummaryDTO)
                .toList();

        // 4. Combinar ambas listas
        List<ClientePublicoGeneralSummaryDTO> listaMixta = new ArrayList<>();
        listaMixta.addAll(listaGenerales);
        listaMixta.addAll(listaNormales);

        return listaMixta;
    }

    @Override
    @Transactional
    public ClientePublicoGeneralDTO getClienteById(String agenteId, String clienteId) {
        ClientePublicoGeneralId id = new ClientePublicoGeneralId(agenteId, clienteId);
        ClientePublicoGeneral cliente = clientePublicoGeneralRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente Publico General no encontrado")
        );
        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public ClientePublicoGeneralDTO save(ClientePublicoGeneralDTO dto) {

        ClientePublicoGeneral cliente = clienteMapper.toEntity(dto);

        ClientePublicoGeneralId id =
                new ClientePublicoGeneralId(
                        dto.getAgente(),
                        dto.getClave()
                );

        cliente.setId(id);

        ClientePublicoGeneral saved =
                clientePublicoGeneralRepository.save(cliente);

        return clienteMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ClientePublicoGeneralDTO update(ClientePublicoGeneralDTO dto, String idAgente, String idCliente) {
        ClientePublicoGeneralId id = new ClientePublicoGeneralId(idAgente,idCliente);

        ClientePublicoGeneral clientePublicoGeneral = clientePublicoGeneralRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Cliente Publico General no encontrado")
                );

        clienteMapper.updateEntityFromDto(dto,clientePublicoGeneral);

        ClientePublicoGeneral updated = clientePublicoGeneralRepository.save(clientePublicoGeneral);

        return clienteMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(String idAgente, String idCliente) {
        ClientePublicoGeneralId id = new ClientePublicoGeneralId(idAgente,idCliente);
        if (!clientePublicoGeneralRepository.existsById(id)){
            throw new ResourceNotFoundException("Cliente Publico General no encontrado");
        }
        clientePublicoGeneralRepository.deleteById(id);
    }
}
