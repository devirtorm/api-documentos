package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.AgenteDTO;
import com.sole.api_documentos.service.AgenteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/agente")
public class AgenteController {
    private final AgenteService agenteService;

    @GetMapping
    public ResponseEntity<List<AgenteDTO>> getAllAgentes(){
        List<AgenteDTO> agentes = agenteService.getAll();
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteDTO> getAgenteById(
            @PathVariable String id
    ){
        AgenteDTO agenteDTO = agenteService.getById(id);
        return ResponseEntity.ok(agenteDTO);
    }
}
