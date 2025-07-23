package com.gse.firma.firma_api.service;

import com.gse.firma.firma_api.dto.FirmaRequest;
import com.gse.firma.firma_api.dto.FirmaResponse;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FirmaService {

    private final ConcurrentHashMap<String, FirmaResponse> firmas = new ConcurrentHashMap<>();

    public FirmaResponse firmar(FirmaRequest request) {
        String hash = generarHash(request.getContenido());
        String firmaId = UUID.randomUUID().toString();
        FirmaResponse response = new FirmaResponse(firmaId, hash);
        firmas.put(firmaId, response);
        return response;
    }

    public FirmaResponse obtenerFirma(String id) {
        FirmaResponse response = firmas.get(id);
        if (response == null) {
            throw new IllegalArgumentException("No se encontró una firma con el ID: " + id);
        }
        return response;
    }

    private String generarHash(String contenido) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(contenido.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando el hash", e);
        }
    }
}
