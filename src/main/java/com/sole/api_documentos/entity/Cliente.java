package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "VCliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "Clave")
    private String clave;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "NombreComercial")
    private String nombreComercial;

    @Column(name = "RFC")
    private String rfc;

    @Column(name = "CURP")
    private String curp;

    @Column(name = "Calle")
    private String calle;

    @Column(name = "NumeroExterior")
    private String numeroExterior;

    @Column(name = "NumeroInterior")
    private String numeroInterior;

    @Column(name = "Colonia")
    private String colonia;

    @Column(name = "CodigoPostal")
    private String codigoPostal;

    @Column(name = "Ciudad")
    private String ciudad;

    @Column(name = "Municipio")
    private String municipio;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "Pais")
    private String pais;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Celular")
    private String celular;

    @Column(name = "EMailPrincipal")
    private String emailPrincipal;

    @Column(name = "Activo")
    private String activo;

    @Column(name = "Agente")
    private String agente;

    @Column(name = "ListaPrecios")
    private String listaPrecios;

    @Column(name = "Descuento1")
    private String descuento1;

    @Column(name = "Descuento2")
    private String descuento2;

    @Column(name = "Descuento3")
    private String descuento3;

    @Column(name = "FechaInicialDescuentos")
    private Date fechaInicialDescuentos;

    @Column(name = "FechaFinalDescuentos")
    private Date fechaFinalDescuentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moneda")
    private Moneda moneda;
}