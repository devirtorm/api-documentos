package com.sole.api_documentos.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetalleRequest {
    private String articulo;
    private String descripcion;
    private BigDecimal cantidad;
    private BigDecimal precio; // Precio base sin descuentos
    
    // Descuentos por artículo
    private BigDecimal descuento1;
    private BigDecimal descuento2;
    private BigDecimal descuento3;
}
