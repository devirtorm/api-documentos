package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.IdClass;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ClasificacionId.class)
@Table(name = "FClasificacion")
public class Clasificacion {
    @Id
    @Column(name = "Clave")
    private String clave;

    @Id
    @Column(name = "ClasificaID")
    private String clasificacionId;

    @Column(name = "Descripcion")
    private String descripcion;
}
