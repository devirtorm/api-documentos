package com.sole.api_documentos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloSummaryDTO {
    private String clave;
    private String descripcion;

    private BigDecimal precio1;
    private BigDecimal precio2;
    private BigDecimal precio3;

    private BigDecimal descuento2;
    private BigDecimal descuento1;
    private BigDecimal descuento3;

    private double existenciaTotal;
    private String unidadPrimaria;
    private String imagen;
}
