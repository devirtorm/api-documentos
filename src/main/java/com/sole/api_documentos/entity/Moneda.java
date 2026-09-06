package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GMoneda")
public class Moneda {
    @Id
    @Column(name = "Clave")
    private String clave;

    @Column(name = "Descripcion")
    private String nombre;

    @Column(name = "MonedaSingularImp")
    private String monedaSingularImp;

    @Column(name = "MonedaPluralImp")
    private String monedaPluralImp;

    @Column(name = "AbreviaturaImp")
    private String abreviaturaImp;

    @Column(name = "MonedaXML")
    private String monedaXml;

}
