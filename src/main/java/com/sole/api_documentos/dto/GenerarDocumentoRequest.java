package com.sole.api_documentos.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class GenerarDocumentoRequest {
    private String tipoDocumento; // 'P' para Pedido, 'M' para Remisión
    private String cliProv;
    private String agente;
    private String almacen;
    private String claveMoneda;
    
    // Descuentos globales (por cliente)
    private BigDecimal descuento1;
    private BigDecimal descuento2;
    private BigDecimal descuento3;
    
    private List<DetalleRequest> detalles;
}
