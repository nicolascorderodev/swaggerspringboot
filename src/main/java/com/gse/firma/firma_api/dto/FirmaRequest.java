package com.gse.firma.firma_api.dto;
import jakarta.validation.constraints.NotBlank;
import com.gse.firma.firma_api.validation.Base64Valido;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para firmar un archivo")
public class FirmaRequest {
    @NotBlank(message = "no debe estar vacío")
    @Base64Valido(message = "no es una cadena base64 válida")
    @Schema(description = "Contenido del archivo en base64", example = "U29sbyBwcmltZXIgdGV4dG8=", required = true)
    private String contenido;

    // Getters y Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
