package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.AgenteDTO;
import com.sole.api_documentos.DTO.ArticuloSummaryDTO;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticuloService {
    ArticuloSummaryDTO getById(String clave);
    Page<ArticuloSummaryDTO> getAll(Pageable pageable, String search);
}
