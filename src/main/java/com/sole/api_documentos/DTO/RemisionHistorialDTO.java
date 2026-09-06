package com.sole.api_documentos.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RemisionHistorialDTO {
    private String folio;
    private String fecha;
    private String cliProv;
    private String agente;
    private String status;
    private Double total;
    private List<RemisionDetalleDTO> detalles;
}
