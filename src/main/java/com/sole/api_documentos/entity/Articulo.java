package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "IArticulo")
public class Articulo {
    @Id
    @Column(name = "Clave")
    private String clave;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "DescripcionComplementaria")
    private String descripcionComplementaria;

    @Column(name = "UnidadPrimaria")
    private String unidadPrimaria;

    @Column(name = "UnidadSecundaria")
    private String unidadSecundaria;

    @Column(name = "Equivalencia")
    private Double equivalencia;

    @Column(name = "Precio1")
    private String precio1;

    @Column(name = "Precio2")
    private String precio2;

    @Column(name = "Precio3")
    private String precio3;

    @Column(name = "Precio4")
    private String precio4;

    @Column(name = "Precio5")
    private String precio5;

    @Column(name = "Descuento1")
    private String descuento1;

    @Column(name = "Descuento2")
    private String descuento2;

    @Column(name = "Descuento3")
    private String descuento3;

    @Column(name = "Clase")
    private String clase;

    @ManyToOne
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "Grupo", referencedColumnName = "Clave")),
        @JoinColumnOrFormula(formula = @JoinFormula(value = "'IG'", referencedColumnName = "ClasificaID"))
    })
    private Clasificacion clasificacion;

    @Column(name = "TipoArticulo")
    private String tipoArticulo;

    @Column(name = "ExistenciaTotal")
    private double existenciaTotal;

    @Column(name = "FechaAlta")
    private String fechaAlta;

    @Column(name = "FechaInicialDescuentos")
    private LocalDateTime fechaInicialDescuentos;

    @Column(name = "FechaFinalDescuentos")
    private LocalDateTime fechaFinalDescuentos;

    @Column(name = "Imagen")
    private String imagen;

    @Column(name = "Activo")
    private String activo;

}
