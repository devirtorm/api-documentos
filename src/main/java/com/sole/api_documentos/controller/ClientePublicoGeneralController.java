package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.service.ClientePublicoGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clientes-mixtos")
@RequiredArgsConstructor
public class ClientePublicoGeneralController {
    private final ClientePublicoGeneralService clientePublicoGeneralService;

    @GetMapping("/{idAgente}/offline")
    public ResponseEntity<List<ClientePublicoGeneralSummaryDTO>> getMixedClientsByAgenteOffline(
            @PathVariable String idAgente,
            @RequestParam String idUsuario) {

        List<ClientePublicoGeneralSummaryDTO> clientes = clientePublicoGeneralService.getAllMixedClientByAgente(idUsuario, idAgente);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idAgente}")
    public ResponseEntity<Page<ClientePublicoGeneralSummaryDTO>> getPagedMixedClientsByAgente(
            @PathVariable String idAgente,
            @RequestParam String idUsuario,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String diaRevision,
            Pageable pageable) {

        Page<ClientePublicoGeneralSummaryDTO> pagedClientes = 
                clientePublicoGeneralService.getPagedMixedClientsByAgente(idUsuario, idAgente, search, diaRevision, pageable);
        return ResponseEntity.ok(pagedClientes);
    }

    @GetMapping("/agente/{agenteId}")
    public ResponseEntity<ClientePublicoGeneralDTO> getById(
            @PathVariable String agenteId,
            @RequestParam String clienteId
    ){
        ClientePublicoGeneralDTO cliente = clientePublicoGeneralService.getClienteById(agenteId,clienteId);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping ("/agente/{agenteId}/last-clave")
    public ResponseEntity<String> getLastClaveByAgente(
            @PathVariable String agenteId
    ){
        String lastClave = clientePublicoGeneralService.getLastClaveByAgente(agenteId);
        return ResponseEntity.ok(lastClave);
    }

    @PostMapping
    public ResponseEntity<ClientePublicoGeneralDTO> save(
            @RequestBody ClientePublicoGeneralDTO dto) {

        ClientePublicoGeneralDTO cliente =
                clientePublicoGeneralService.save(dto);

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{idAgente}")
    public ResponseEntity<ClientePublicoGeneralDTO> update(
            @PathVariable String idAgente,
            @RequestParam String idCliente,
            @RequestBody ClientePublicoGeneralDTO clientePublicoGeneralDTO
    ){
        ClientePublicoGeneralDTO cliente = clientePublicoGeneralService.update(clientePublicoGeneralDTO, idAgente, idCliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{idAgente}/{idCliente}")
    public ResponseEntity<Void> delete(
            @PathVariable String idAgente,
            @PathVariable String idCliente
    ){
        clientePublicoGeneralService.deleteById(idAgente,idCliente);
        return ResponseEntity.noContent().build();
    }

}
