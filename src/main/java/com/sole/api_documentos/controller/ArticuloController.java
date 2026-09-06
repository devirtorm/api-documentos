package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.ArticuloSummaryDTO;
import com.sole.api_documentos.service.ArticuloService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/articulos")
@RequiredArgsConstructor
public class ArticuloController {
    private final ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<Page<ArticuloSummaryDTO>> getAllArticulos(
        @RequestParam(required = false) String search,
        @PageableDefault(sort = "descripcion") Pageable pageable
    ){
        Page<ArticuloSummaryDTO> articuloSummaryDTOS = articuloService.getAll(pageable,search);
        return ResponseEntity.ok(articuloSummaryDTOS);
    }

    @GetMapping("/{agenteId}")
    public ResponseEntity<ArticuloSummaryDTO> getAllArticulosById(
            @PathVariable String agenteId
    ){
        ArticuloSummaryDTO articuloSummaryDTO = articuloService.getById(agenteId);
        return ResponseEntity.ok(articuloSummaryDTO);
    }
}
