package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.AgenteDTO;

import java.util.List;

public interface AgenteService {
    AgenteDTO getById(String clave);
    List<AgenteDTO> getAll();
    void delete();
}
