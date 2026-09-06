package com.sole.api_documentos.security.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String user;
    private String agente;


    public LoginResponseDTO(String user, String agente) {
        this.user = user;
        this.agente = agente;
    }

}
