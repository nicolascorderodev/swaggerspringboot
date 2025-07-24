package com.gse.firma.firma_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "firmas")
public class FirmaDocument {

    @Id
    private String firmaId;
    private String hash;

    public FirmaDocument() {
    }

    public FirmaDocument(String firmaId, String hash) {
        this.firmaId = firmaId;
        this.hash = hash;
    }

    public String getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(String firmaId) {
        this.firmaId = firmaId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}