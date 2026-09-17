package com.sole.api_documentos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class GenerarDocumentoResponse {
    private boolean success;
    private String mensaje;
    private DatosDocumento datos;

    @Data
    @Builder
    public static class DatosDocumento {
        private String folioGenerado;
        private String tipoDocumento;
        private LocalDateTime fechaGeneracion;
        private Totales totales;
    }

    @Data
    @Builder
    public static class Totales {
        private BigDecimal subtotalGravado;
        private BigDecimal subtotalExento;
        private BigDecimal descuento;
        private BigDecimal iva;
        private BigDecimal totalFinal;
    }
}
