package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.ClientePublicoGeneralDTO;
import com.sole.api_documentos.DTO.ClientePublicoGeneralSummaryDTO;
import com.sole.api_documentos.service.ClientePublicoGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clientes-mixtos")
@RequiredArgsConstructor
public class ClientePublicoGeneralController {
    private final ClientePublicoGeneralService clientePublicoGeneralService;
    @GetMapping("/{idAgente}")
    public ResponseEntity<List<ClientePublicoGeneralSummaryDTO>> getMixedClientsByAgente(
            @PathVariable String idAgente,
            @RequestParam String idUsuario) {

        List<ClientePublicoGeneralSummaryDTO> clientes = clientePublicoGeneralService.getAllMixedClientByAgente(idUsuario, idAgente);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/agente/{agenteId}")
    public ResponseEntity<ClientePublicoGeneralDTO> getById(
            @PathVariable String agenteId,
            @RequestParam String clienteId
    ){
        ClientePublicoGeneralDTO cliente = clientePublicoGeneralService.getClienteById(agenteId,clienteId);
        return ResponseEntity.ok(cliente);
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
            @RequestParam String idUsuario,
            @RequestBody ClientePublicoGeneralDTO clientePublicoGeneralDTO
    ){
        ClientePublicoGeneralDTO cliente = clientePublicoGeneralService.update(clientePublicoGeneralDTO, idAgente, idUsuario);
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
