package com.gse.firma.firma_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para firmar un archivo")
public class FirmaRequest {

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
