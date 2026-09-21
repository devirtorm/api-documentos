package com.sole.api_documentos.security.dto;

import lombok.Data;

@Data
public class LicenciaPayload {
    private String claveIngresada;
    private String idDispositivo;
    private String serialServidor;
    private String nombreDispositivo;
    private String empresa;
    private String rfcEmpresa;
}
