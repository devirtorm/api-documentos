package com.sole.api_documentos.security.dto;

import lombok.Data;

@Data
public class RegistroDispositivoRequest {
    private String nombre;
    private String clave;
    private String idDispositivo;
}
