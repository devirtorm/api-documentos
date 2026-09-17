package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.InventarioProjection;
import com.sole.api_documentos.service.ArticuloAlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/inventario")
@RequiredArgsConstructor
public class ArticuloAlmacenController {

    private final ArticuloAlmacenService articuloAlmacenService;

    @GetMapping("/almacen/{almacen}")
    public ResponseEntity<Page<InventarioProjection>> getInventarioByAlmacen(
            @PathVariable String almacen,
            @RequestParam(required = false) String search,
            @PageableDefault Pageable pageable) {
        
        Page<InventarioProjection> inventario = articuloAlmacenService.getInventarioByAlmacen(almacen, search, pageable);
        
        return ResponseEntity.ok(inventario);
    }
}
