package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.ClienteDetailDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.DTO.ClienteSummaryDTO;
import com.sole.api_documentos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<ClienteSummaryDTO>> getAllAgentes(){
        List<ClienteSummaryDTO> agentes = clienteService.getAll();
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener por un solo cliente")
    public ResponseEntity<ClienteDetailDTO> getAgenteById(@PathVariable String id){
        ClienteDetailDTO clienteDTO = clienteService.getById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("agente/{id}")
    @Operation(summary = "Obtener clientes por agente")
    public ResponseEntity<List<ClienteSummaryDTO>> getClientesByAgente(@PathVariable String id){
        List<ClienteSummaryDTO> clientesByAgente = clienteService.getAllClientesByAgente(id);
        return ResponseEntity.ok(clientesByAgente);
    }

}
