package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VPedidoD")
public class PedidoDetalle {
    @EmbeddedId
    private RemisionDetalleId id;

    @Column(name = "Articulo")
    private String articulo;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Cantidad")
    private Double cantidad;

    @Column(name = "Precio")
    private Double precio;

    @Column(name = "ImporteTotal")
    private Double importeTotal;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("folio")
    @JoinColumn(name = "Folio", referencedColumnName = "Folio")
    private Pedido pedido;

}
