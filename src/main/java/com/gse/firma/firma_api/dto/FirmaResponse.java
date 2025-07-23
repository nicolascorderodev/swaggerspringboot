package com.gse.firma.firma_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del proceso de firma")
public class FirmaResponse {

    @Schema(description = "ID único de la firma")
    private String firmaId;

    @Schema(description = "Hash generado del contenido", example = "abc123hash...")
    private String hash;

    public FirmaResponse(String firmaId, String hash) {
        this.firmaId = firmaId;
        this.hash = hash;
    }

    // Getters
    public String getFirmaId() {
        return firmaId;
    }

    public String getHash() {
        return hash;
    }
}
