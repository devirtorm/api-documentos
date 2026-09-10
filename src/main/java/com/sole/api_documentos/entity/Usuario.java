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
@Table(name = "SUsuarioClienteWeb", schema = "dbo")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "Clave")
    private String clave;

    @Column(name = "Agente")
    private String agente;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Almacen")
    private String almacen;

    @Column(name = "TipoUsuario")
    private String tipoUsuario;

    @Column(name = "ClientePublicoGeneral")
    private String clientePublicoGeneral;

    @Column(name = "UsuarioSistema")
    private String usuarioSistema;
}
