package com.sole.api_documentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Table (name = "IArticuloAlmacen") 
public class ArticuloAlmacen {
    
    @EmbeddedId
    private ArticuloAlmacenId id;
    @Column(name = "Existencia")
    private int existencia; 
}
