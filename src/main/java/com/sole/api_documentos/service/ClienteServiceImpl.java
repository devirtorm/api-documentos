package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ClienteDetailDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.DTO.ClienteSummaryDTO;
import com.sole.api_documentos.entity.Cliente;
import com.sole.api_documentos.entity.ClientePublicoGeneral;
import com.sole.api_documentos.entity.Usuario;
import com.sole.api_documentos.exception.ResourceNotFoundException;
import com.sole.api_documentos.mapper.ClienteMapper;
import com.sole.api_documentos.repository.ClientePublicoGeneralRepository;
import com.sole.api_documentos.repository.ClienteRepository;
import com.sole.api_documentos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final UsuarioRepository usuarioRepository;
    private final ClientePublicoGeneralRepository clientePublicoGeneralRepository;

    @Override
    public ClienteDetailDTO getById(String clave) {
        Cliente cliente = clienteRepository.findById(clave).orElseThrow(
                ()-> new ResourceNotFoundException("No se encontró el cliente con clave: " + clave)
        );
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteSummaryDTO> getAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    @Override
    public List<ClienteSummaryDTO> getAllClientesByAgente(String clave) {
        List<Cliente> clientesByAgente = clienteRepository.findByAgente(clave);
        return clienteMapper.toDTOList(clientesByAgente);
    }

}
