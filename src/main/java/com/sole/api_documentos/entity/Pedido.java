package com.sole.api_documentos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VPedidoE")
public class Pedido {
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

    @OneToMany(mappedBy = "pedido")
    private List<PedidoDetalle> detalles;

}
