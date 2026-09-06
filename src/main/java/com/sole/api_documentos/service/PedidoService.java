package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.PedidoHistorialDTO;
import com.sole.api_documentos.DTO.RemisionHistorialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {
    Page<PedidoHistorialDTO> getHistorialByAgenteId(String id, String search, Pageable pageable);
    Page<PedidoHistorialDTO> getHistorialByClienteId(String id, Pageable pageable);
}
