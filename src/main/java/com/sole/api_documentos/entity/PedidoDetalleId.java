package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PedidoDetalleId implements Serializable {
    @Column(name = "Consecutivo")
    private Integer consecutivo;

    @Column(name = "Folio")
    private String folio;
}
