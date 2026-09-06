package com.sole.api_documentos.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VRemisionE")
public class Remision {
    @Id
    @Column(name = "Folio")
    private String folio;

    @Column(name = "Fecha")
    private String fecha;

    @Column(name = "CliProv")
    private String cliProv;

    @Column(name = "Agente")
    private String agente;

    @Column(name = "Status")
    private String status;

    @Column(name = "Total")
    private Double total;

    @OneToMany(mappedBy = "remision")
    private List<RemisionDetalle> detalles;
}
