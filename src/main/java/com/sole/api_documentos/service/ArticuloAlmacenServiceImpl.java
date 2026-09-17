package com.sole.api_documentos.service;

import com.sole.api_documentos.DTO.InventarioProjection;
import com.sole.api_documentos.repository.ArticuloAlmacenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticuloAlmacenServiceImpl implements ArticuloAlmacenService {

    private final ArticuloAlmacenRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<InventarioProjection> getInventarioByAlmacen(String almacen, String search, Pageable pageable) {
        String searchValue = (search != null && !search.trim().isEmpty()) ? search : null;
        return repository.findInventarioByAlmacen(almacen, searchValue, pageable);
    }
}
