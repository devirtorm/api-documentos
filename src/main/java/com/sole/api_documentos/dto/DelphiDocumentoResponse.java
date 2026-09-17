package com.sole.api_documentos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DelphiDocumentoResponse {

    @JsonProperty("Documentos")
    private List<Documento> documentos;
    
    @JsonProperty("Estatus")
    private Integer estatus;

    @Data
    public static class Documento {
        @JsonProperty("Folio")
        private String folio;
        
        @JsonProperty("Subtotal")
        private BigDecimal subtotal;
        
        @JsonProperty("Descuento")
        private BigDecimal descuento;
        
        @JsonProperty("Iva")
        private BigDecimal iva;
        
        @JsonProperty("Ieps")
        private BigDecimal ieps;
        
        @JsonProperty("RetIva")
        private BigDecimal retIva;
        
        @JsonProperty("RetIsr")
        private BigDecimal retIsr;
        
        @JsonProperty("Total")
        private BigDecimal total;
    }
}
