package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VClienteAgenteWeb")
public class ClientePublicoGeneral {

    @EmbeddedId
    private ClientePublicoGeneralId id;

    @Column(name = "Clave", insertable = false, updatable = false)
    private String clave;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Domicilio")
    private String domicilio;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Ciudad")
    private String ciudad;

    @Column(name = "DiaRevision")
    private String diaRevision;

    @Column(name = "Extra1")
    private String extra1;

    @Column(name = "Extra2")
    private String extra2;

    @Column(name = "Extra3")
    private String extra3;

    @Column(name = "Extra4")
    private String extra4;

    @Column(name = "ExtraN1")
    private String extraN1;

    @Column(name = "ExtraN2")
    private String extraN2;

    @Column(name = "ExtraN3")
    private String extraN3;

    @Column(name = "ExtraN4")
    private String extraN4;
}
