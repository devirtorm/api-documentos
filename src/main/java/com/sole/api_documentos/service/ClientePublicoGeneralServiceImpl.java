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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // Obtener configuración del usuario y cliente base
        Usuario configUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario web no encontrado"));

        Cliente clienteBase = clienteRepository.findById(configUsuario.getClientePublicoGeneral())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente Base no encontrado"));

        // Obtener y mapear los clientes de público general
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
    public Page<ClientePublicoGeneralSummaryDTO> getPagedMixedClientsByAgente(
            String idUsuario, String idAgente, String search, String diaRevision, Pageable pageable) {
        
        List<ClientePublicoGeneralSummaryDTO> allClients = getAllMixedClientByAgente(idUsuario, idAgente);
        Stream<ClientePublicoGeneralSummaryDTO> stream = allClients.stream();

        if (search != null && !search.trim().isEmpty()) {
            String lowerSearch = search.trim().toLowerCase();
            stream = stream.filter(c -> 
                (c.getNombre() != null && c.getNombre().toLowerCase().contains(lowerSearch)) ||
                (c.getClave() != null && c.getClave().toLowerCase().contains(lowerSearch))
            );
        }

        if (diaRevision != null && !diaRevision.trim().isEmpty()) {
            stream = stream.filter(c -> diaRevision.equalsIgnoreCase(c.getDiaRevision()));
        }

        List<ClientePublicoGeneralSummaryDTO> filtered = stream.collect(Collectors.toList());

        if (pageable.getSort().isSorted()) {
            Comparator<ClientePublicoGeneralSummaryDTO> comparator = null;
            for (Sort.Order order : pageable.getSort()) {
                Comparator<ClientePublicoGeneralSummaryDTO> current = (c1, c2) -> {
                    switch(order.getProperty()) {
                        case "ordenVisita": {
                            // Comparación numérica; nulos van al final
                            Integer n1 = parseOrdenVisita(c1.getOrdenVisita());
                            Integer n2 = parseOrdenVisita(c2.getOrdenVisita());
                            if (n1 == null && n2 == null) return 0;
                            if (n1 == null) return 1;
                            if (n2 == null) return -1;
                            return order.isAscending() ? n1.compareTo(n2) : n2.compareTo(n1);
                        }
                        default: {
                            String val1 = "", val2 = "";
                            switch(order.getProperty()) {
                                case "nombre": val1 = c1.getNombre(); val2 = c2.getNombre(); break;
                                case "clave": val1 = c1.getClave(); val2 = c2.getClave(); break;
                                case "diaRevision": val1 = c1.getDiaRevision(); val2 = c2.getDiaRevision(); break;
                                case "ciudad": val1 = c1.getCiudad(); val2 = c2.getCiudad(); break;
                            }
                            if(val1 == null) val1 = "";
                            if(val2 == null) val2 = "";
                            return order.isAscending() ? val1.compareToIgnoreCase(val2) : val2.compareToIgnoreCase(val1);
                        }
                    }
                };
                comparator = (comparator == null) ? current : comparator.thenComparing(current);
            }
            if (comparator != null) {
                filtered.sort(comparator);
            }
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        List<ClientePublicoGeneralSummaryDTO> pageContent = (start <= end) ? filtered.subList(start, end) : new ArrayList<>();

        return new PageImpl<>(pageContent, pageable, filtered.size());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientePublicoGeneralDTO getClienteById(String agenteId, String clienteId) {
        ClientePublicoGeneralId id = new ClientePublicoGeneralId(agenteId, clienteId);
        Optional<ClientePublicoGeneral> opt = clientePublicoGeneralRepository.findById(id);
        if (opt.isPresent()) {
            return clienteMapper.toDto(opt.get());
        }
        
        // Si no es cliente de público general, buscar en clientes normales (VCliente)
        Cliente normal = clienteRepository.findById(clienteId).orElseThrow(
                () -> new ResourceNotFoundException("Cliente no encontrado: " + clienteId)
        );
        ClientePublicoGeneralDTO dto = new ClientePublicoGeneralDTO();
        dto.setAgente(normal.getAgente());
        dto.setClave(normal.getClave());
        dto.setNombre(normal.getNombre());
        dto.setTelefono(normal.getTelefono());
        dto.setCiudad(normal.getCiudad());
        dto.setDiaRevision("");
        dto.setDescuento1(normal.getDescuento1());
        dto.setDescuento2(normal.getDescuento2());
        dto.setDescuento3(normal.getDescuento3());
        dto.setListaPrecios(normal.getListaPrecios());
        dto.setFechaInicialDescuentos(normal.getFechaInicialDescuentos());
        dto.setFechaFinalDescuentos(normal.getFechaFinalDescuentos());
        return dto;
    }

    @Override
    @Transactional
    public ClientePublicoGeneralDTO save(ClientePublicoGeneralDTO dto) {

        String clave = resolverClaveDisponible(dto.getAgente(), dto.getClave());
        dto.setClave(clave);

        ClientePublicoGeneral cliente = clienteMapper.toEntity(dto);
        cliente.setId(new ClientePublicoGeneralId(dto.getAgente(), clave));

        ClientePublicoGeneral saved = clientePublicoGeneralRepository.save(cliente);

        return clienteMapper.toDto(saved);
    }

    /**
     * Si la clave ya existe para el agente, la incrementa en 1 y repite
     * hasta encontrar una clave libre.
     * Soporta claves puramente numéricas ("0001") y con prefijo ("CL001", "A01").
     */
    private String resolverClaveDisponible(String agente, String claveOriginal) {
        String clave = claveOriginal;
        while (clientePublicoGeneralRepository.existsById(new ClientePublicoGeneralId(agente, clave))) {
            clave = incrementarClave(clave);
        }
        return clave;
    }

    /**
     * Incrementa el sufijo numérico de la clave manteniendo longitud y prefijo.
     * Ejemplos:
     *   "0001"  → "0002"
     *   "CL001" → "CL002"
     *   "CL099" → "CL100"
     */
    private String incrementarClave(String clave) {
        // Separar prefijo alfabético del sufijo numérico al final
        int i = clave.length() - 1;
        while (i >= 0 && Character.isDigit(clave.charAt(i))) {
            i--;
        }
        String prefijo = clave.substring(0, i + 1);
        String parteNumerica = clave.substring(i + 1);

        if (parteNumerica.isEmpty()) {
            // Caso extremo: la clave no termina en dígitos, añadir "1"
            return clave + "1";
        }

        int longitud = parteNumerica.length();
        int numero = Integer.parseInt(parteNumerica);
        return prefijo + String.format("%0" + longitud + "d", numero + 1);
    }

    private Integer parseOrdenVisita(String valor) {
        if (valor == null || valor.trim().isEmpty()) return null;
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return null;
        }
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

    @Override
    @Transactional
    public String getLastClaveByAgente(String agente) {
        return clientePublicoGeneralRepository
                .findFirstByIdAgenteOrderByIdClaveDesc(agente)
                .map(c -> incrementarClave(c.getId().getClave()))
                .orElse(null);
    }
}
