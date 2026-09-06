package com.sole.api_documentos.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClientePublicoGeneralId implements Serializable {
    @Column(name = "Agente")
    private String agente;

    @Column(name = "Clave")
    private String clave;
}
