package com.sole.api_documentos.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasificacionId implements Serializable {
    private String clave;
    private String clasificacionId;
}
