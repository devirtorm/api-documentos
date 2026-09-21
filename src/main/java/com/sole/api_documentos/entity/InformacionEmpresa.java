package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FInformacionEmpresa")
public class InformacionEmpresa {

    @Id
    @Column(name = "RFC")
    private String rfc;

    @Column(name = "NombreEmp")
    private String nombreEmpresa;
}
