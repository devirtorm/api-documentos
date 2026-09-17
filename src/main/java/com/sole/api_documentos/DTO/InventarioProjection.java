package com.sole.api_documentos.DTO;

public interface InventarioProjection {
    String getArticulo();
    String getDescripcion();
    Integer getExistencia();
    Integer getVendida();
}
