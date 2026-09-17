package com.sole.api_documentos.controller;

import com.sole.api_documentos.dto.GenerarDocumentoRequest;
import com.sole.api_documentos.dto.GenerarDocumentoResponse;
import com.sole.api_documentos.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping("/generar")
    public ResponseEntity<GenerarDocumentoResponse> generarDocumento(@RequestBody GenerarDocumentoRequest request) {
        GenerarDocumentoResponse response = documentoService.procesarDocumento(request);
        return ResponseEntity.ok(response);
    }
}
