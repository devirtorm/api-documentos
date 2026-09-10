package com.sole.api_documentos.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Embeddable 
public class ArticuloAlmacenId implements Serializable{
    @Column(name = "Articulo")
    private String articulo;

    @Column(name = "Almacen")
    private String almacen;
}
