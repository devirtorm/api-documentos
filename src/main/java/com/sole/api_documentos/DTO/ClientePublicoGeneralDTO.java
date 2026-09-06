package com.sole.api_documentos.DTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePublicoGeneralDTO {

    @NotBlank(message = "El agente es obligatorio")
    private String agente;

    @NotBlank(message = "La clave es obligatoria")
    private String clave;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String telefono;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El día de revisión es obligatorio")
    private String diaRevision;

    private String direccion;
    private String extra1;
    private String extra2;
    private String extra3;
    private String extra4;
    private String extraN1;
    private String extraN2;
    private String extraN3;
    private String extraN4;
}