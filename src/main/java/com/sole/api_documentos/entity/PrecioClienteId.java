package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecioClienteId implements Serializable {
    @Column(name = "Cliente")
    private String cliente;

    @Column(name = "Articulo")
    private String articulo;
}
