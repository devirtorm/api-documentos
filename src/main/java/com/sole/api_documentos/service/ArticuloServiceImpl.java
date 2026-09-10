package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.ArticuloSummaryDTO;
import com.sole.api_documentos.entity.Articulo;
import com.sole.api_documentos.exception.ResourceNotFoundException;
import com.sole.api_documentos.mapper.ArticuloMapper;
import com.sole.api_documentos.repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticuloServiceImpl implements ArticuloService{
    private final ArticuloRepository articuloRepository;
    private final ArticuloMapper articuloMapper;

    @Override
    public ArticuloSummaryDTO getById(String clave) {
        Articulo articulo = articuloRepository.findById(clave).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el artículo con clave: " + clave)
        );
        return articuloMapper.toDTO(articulo);
    }

    @Override 
    public Page<ArticuloSummaryDTO> getAll(Pageable pageable, String search) {
        Page<Articulo> articulosPage = articuloRepository.findBySearch(search, pageable);
        return articulosPage.map(articuloMapper::toDTO);
    }

    @Override
    public Page<ArticuloSummaryDTO> getAllByAlmacen(Pageable pageable, String search, String almacen) {
        Page<Articulo> articulosPage = articuloRepository.findBySearchAndAlmacen(search, almacen, pageable);
        return articulosPage.map(articuloMapper::toDTO);
    }
}
