package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ClienteDetailDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.DTO.ClienteSummaryDTO;

import java.util.List;

public interface ClienteService {
    ClienteDetailDTO getById(String clave);
    List<ClienteSummaryDTO> getAll();
    List<ClienteSummaryDTO> getAllClientesByAgente(String clave);
}
