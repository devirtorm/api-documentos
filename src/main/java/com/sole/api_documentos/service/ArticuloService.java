package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ArticuloSummaryDTO;
import com.sole.api_documentos.DTO.PrecioClienteDTO;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticuloService {
    ArticuloSummaryDTO getById(String clave);
    Page<ArticuloSummaryDTO> getAll(Pageable pageable, String search);
    Page<ArticuloSummaryDTO> getAllByAlmacen(Pageable pageable, String search, String almacen);
    List<ArticuloSummaryDTO> getAllByAlmacenOffline(String almacen);
    List<PrecioClienteDTO> getPreciosEspecialesPorCliente(String cliente);
    List<PrecioClienteDTO> getAllPreciosEspeciales();
    List<PrecioClienteDTO> getPreciosEspecialesPorAgente(String agente);
}
