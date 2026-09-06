package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VRemisionD")
public class RemisionDetalle {
    @EmbeddedId
    private RemisionDetalleId id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("folio")
    @JoinColumn(name = "Folio", referencedColumnName = "Folio")
    private Remision remision;

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

}
