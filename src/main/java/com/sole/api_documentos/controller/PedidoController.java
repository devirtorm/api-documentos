package com.sole.api_documentos.controller;

import com.sole.api_documentos.DTO.PedidoHistorialDTO;
import com.sole.api_documentos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping("/{agenteId}")
    public ResponseEntity<Page<PedidoHistorialDTO>> getPedidosByAgenteId(
            @PathVariable String agenteId,
            @RequestParam(required = false) String search,
            @PageableDefault(sort = "fecha") Pageable pageable){

        Page<PedidoHistorialDTO> pedidos = pedidoService.getHistorialByAgenteId(agenteId, search, pageable);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{agenteId}/{clienteId}")
    public ResponseEntity<Page<PedidoHistorialDTO>> getPedidosByClienteId(
            @PathVariable String agenteId,
            @PathVariable String clienteId,
            @PageableDefault(sort = "fecha") Pageable pageable){
        Page<PedidoHistorialDTO> pedidos = pedidoService.getHistorialByClienteId(clienteId, pageable);
        return ResponseEntity.ok(pedidos);
    }

}
