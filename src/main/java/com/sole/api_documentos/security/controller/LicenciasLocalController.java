package com.sole.api_documentos.security.controller;

import com.sole.api_documentos.security.dto.RegistroDispositivoRequest;
import com.sole.api_documentos.security.service.LicenciasLocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth/licencias")
@RequiredArgsConstructor
public class LicenciasLocalController {

    private final LicenciasLocalService licenciasLocalService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarDispositivoLocal(@RequestBody RegistroDispositivoRequest request) {
        try {
            ResponseEntity<String> respuesta = licenciasLocalService.procesarRegistroDispositivo(request);
            return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta.getBody());

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            System.err.println("Error validando en el servidor de licencias (4xx): " + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getResponseBodyAsString());

        } catch (RuntimeException e) {
            System.err.println("Error interno de licenciamiento: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");

        } catch (Exception e) {
            System.err.println("Error validando en el servidor de licencias: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"No se pudo conectar al servidor de licencias.\"}");
        }
    }
}
