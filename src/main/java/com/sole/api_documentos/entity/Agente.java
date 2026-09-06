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
@Table(name = "VAgente")
@AllArgsConstructor
@NoArgsConstructor
public class Agente {
    @Id
    @Column(name = "Clave")
    private String clave;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Curp")
    private String curp;

    @Column(name = "Rfc")
    private String rfc;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Tipo")
    private String tipoUsuario;

    @Column(name = "Email")
    private String correo;

    @Column(name = "celular")
    private String celular;
}
