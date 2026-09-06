package com.sole.api_documentos.security.dto;

import lombok.Data;

@Data
public class JwtAuthResponseDTO {
    private String accessToken;
    private String user;
    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
