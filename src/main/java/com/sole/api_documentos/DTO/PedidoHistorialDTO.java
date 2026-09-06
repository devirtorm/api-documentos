package com.sole.api_documentos.DTO;

import com.sole.api_documentos.entity.PedidoDetalleId;
import lombok.Data;

import java.util.List;

@Data
public class PedidoHistorialDTO {
    private String folio;
    private String fecha;
    private String cliProv;
    private String agente;
    private String status;
    private Double total;
    private List<PedidoDetalleDTO> detalles;
}
