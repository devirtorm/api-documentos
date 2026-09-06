package com.sole.api_documentos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgenteDTO {
    private String clave;
    private String nombre;
    private String curp;
    private String rfc;
    private String telefono;
    private String tipoUsuario;
    private String correo;
    private String celular;
}
