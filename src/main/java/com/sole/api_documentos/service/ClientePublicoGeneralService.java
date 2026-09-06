package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.entity.ClientePublicoGeneral;

import java.util.List;

public interface ClientePublicoGeneralService {
    List<ClientePublicoGeneralSummaryDTO> getAllMixedClientByAgente(String idUsuario, String idAgente);
    ClientePublicoGeneralDTO getClienteById(String agenteId, String clienteId);
    ClientePublicoGeneralDTO save(ClientePublicoGeneralDTO dto);
    ClientePublicoGeneralDTO update(ClientePublicoGeneralDTO dto, String idAgente, String idCliente);
    void deleteById(String idAgente, String idCliente);
}
