package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "VPrecioCliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecioCliente {

    @EmbeddedId
    private PrecioClienteId id;

    @Column(name = "PrecioUP")
    private BigDecimal precioUP;

    @Column(name = "PrecioUS")
    private BigDecimal precioUS;

    @Column(name = "Descuento1")
    private BigDecimal descuento1;

    @Column(name = "Descuento2")
    private BigDecimal descuento2;

    @Column(name = "Descuento3")
    private BigDecimal descuento3;

    @Column(name = "FechaInicialPrecios")
    private LocalDateTime fechaInicialPrecios;

    @Column(name = "FechaFinalPrecios")
    private LocalDateTime fechaFinalPrecios;

    @Column(name = "FechaInicialDescuentos")
    private LocalDateTime fechaInicialDescuentos;

    @Column(name = "FechaFinalDescuentos")
    private LocalDateTime fechaFinalDescuentos;

    // We can omit the Extra columns if they are not needed for business logic to keep it clean.
}
