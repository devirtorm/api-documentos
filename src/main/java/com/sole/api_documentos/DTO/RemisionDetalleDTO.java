package com.sole.api_documentos.DTO;

import lombok.Data;

@Data
public class RemisionDetalleDTO {
    private Integer consecutivo;
    private String articulo;
    private String descripcion;
    private Double cantidad;
    private Double precio;
    private Double importeTotal;
}
