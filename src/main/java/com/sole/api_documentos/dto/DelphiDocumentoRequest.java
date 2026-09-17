package com.sole.api_documentos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DelphiDocumentoRequest {

    @JsonProperty("Documentos")
    private List<Documento> documentos;

    @Data
    public static class Documento {
        @JsonProperty("TipoDocumento")
        private String tipoDocumento;
        
        @JsonProperty("CliProv")
        private String cliProv;
        
        @JsonProperty("Agente")
        private String agente;
        
        @JsonProperty("Usuario")
        private String usuario;
        
        @JsonProperty("Almacen")
        private String almacen;
        
        @JsonProperty("Moneda")
        private String moneda;
        
        @JsonProperty("Descuento1")
        private BigDecimal descuento1;
        
        @JsonProperty("Descuento2")
        private BigDecimal descuento2;
        
        @JsonProperty("Descuento3")
        private BigDecimal descuento3;
        
        @JsonProperty("Articulos")
        private List<Articulo> articulos;
    }

    @Data
    public static class Articulo {
        @JsonProperty("Articulo")
        private String articulo;
        
        @JsonProperty("Descripcion")
        private String descripcion;
        
        @JsonProperty("UnidadMedida")
        private String unidadMedida;
        
        @JsonProperty("Cantidad")
        private BigDecimal cantidad;
        
        @JsonProperty("Precio")
        private BigDecimal precio;
        
        @JsonProperty("Descuento1")
        private BigDecimal descuento1;
        
        @JsonProperty("Descuento2")
        private BigDecimal descuento2;
        
        @JsonProperty("Descuento3")
        private BigDecimal descuento3;
    }
}
