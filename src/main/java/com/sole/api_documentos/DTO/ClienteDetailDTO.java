package com.sole.api_documentos.DTO;

import com.sole.api_documentos.entity.Moneda;
import lombok.Data;

import java.util.Date;

@Data
public class ClienteDetailDTO {
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

    private String listaPrecios;
    private String descuento1;
    private String descuento2;
    private String descuento3;
    private Date fechaInicialDescuentos;
    private Date fechaFinalDescuentos;

    private MonedaDTO moneda;
}
