package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.RemisionHistorialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RemisionService {
    Page<RemisionHistorialDTO> getHistorialByAgenteId(String id, String search, Pageable pageable);
    Page<RemisionHistorialDTO> getHistorialByClienteId(String id, Pageable pageable);
}
