package com.sole.api_documentos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecioClienteDTO {
    private String cliente;
    private String articulo;
    private BigDecimal precioUP;
    private BigDecimal precioUS;
    private BigDecimal descuento1;
    private BigDecimal descuento2;
    private BigDecimal descuento3;
    private LocalDateTime fechaInicialPrecios;
    private LocalDateTime fechaFinalPrecios;
    private LocalDateTime fechaInicialDescuentos;
    private LocalDateTime fechaFinalDescuentos;
}
