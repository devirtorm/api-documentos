package com.sole.api_documentos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSummaryDTO {
    private String clave;
    private String nombre;
    private String nombreComercial;
    private String rfc;
    private String curp;

    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String municipio;
    private String estado;
    private String pais;

    private String telefono;
    private String celular;
    private String emailPrincipal;

    private String activo;
}
