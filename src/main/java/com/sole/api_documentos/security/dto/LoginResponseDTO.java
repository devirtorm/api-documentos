package com.sole.api_documentos.security.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String user;
    private String agente;
    private String almacen;

    public LoginResponseDTO(String user, String agente, String almacen) {
        this.user = user;
        this.agente = agente;
        this.almacen = almacen;
    }

}
