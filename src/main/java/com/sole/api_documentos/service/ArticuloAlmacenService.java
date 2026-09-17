package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.InventarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticuloAlmacenService {
    Page<InventarioProjection> getInventarioByAlmacen(String almacen, String search, Pageable pageable);
}
