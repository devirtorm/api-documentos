package com.sole.api_documentos.DTO;
import lombok.Data;

@Data
public class ClientePublicoGeneralSummaryDTO {
    // ---- Datos sobrescritos por el Cliente Público General ----
    private String agente;
    private String clave;
    private String nombre;
    private String telefono;
    private String ciudad;
    private String diaRevision;
    private String ordenVisita;
    private Boolean esClienteBase;


    // ---- Datos heredados del Cliente Base (VCliente) ----
    private String rfc;
    private String listaPrecios;
    private String descuento1;
    private String descuento2;
    private String descuento3;
    private java.util.Date fechaInicialDescuentos;
    private java.util.Date fechaFinalDescuentos;
    private MonedaDTO moneda;

}