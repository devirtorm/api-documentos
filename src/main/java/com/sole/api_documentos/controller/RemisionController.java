package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.RemisionHistorialDTO;
import com.sole.api_documentos.service.RemisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/remision")
public class RemisionController {

    private final RemisionService remisionService;

    @GetMapping("/{agenteId}")
    public ResponseEntity<Page<RemisionHistorialDTO>> getAllRemisionesByAgenteId(
            @PathVariable String agenteId,
            @RequestParam(required = false) String search,
            @PageableDefault(page = 0, size = 10, sort = "fecha") Pageable pageable
    ){
        Page<RemisionHistorialDTO> remisiones = remisionService.getHistorialByAgenteId(agenteId,search,pageable);
        return ResponseEntity.ok(remisiones);
    }
}
