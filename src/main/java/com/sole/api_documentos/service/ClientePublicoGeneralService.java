package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientePublicoGeneralService {
    List<ClientePublicoGeneralSummaryDTO> getAllMixedClientByAgente(String idUsuario, String idAgente);
    Page<ClientePublicoGeneralSummaryDTO> getPagedMixedClientsByAgente(String idUsuario, String idAgente, String search, String diaRevision, Pageable pageable);
    ClientePublicoGeneralDTO getClienteById(String agenteId, String clienteId);
    ClientePublicoGeneralDTO save(ClientePublicoGeneralDTO dto);
    ClientePublicoGeneralDTO update(ClientePublicoGeneralDTO dto, String idAgente, String idCliente);
    void deleteById(String idAgente, String idCliente);
    String getLastClaveByAgente(String agente);
}
