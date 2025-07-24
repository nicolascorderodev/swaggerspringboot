package com.gse.firma.firma_api.service;

import com.gse.firma.firma_api.dto.FirmaRequest;
import com.gse.firma.firma_api.dto.FirmaResponse;
import com.gse.firma.firma_api.model.FirmaDocument;
import com.gse.firma.firma_api.repository.FirmaRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FirmaService {

    private final FirmaRepository firmaRepository;

    public FirmaService(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    public FirmaResponse firmar(FirmaRequest request) {
        String contenido = request.getContenido();
        String hash = generarHash(contenido);
        String firmaId = UUID.randomUUID().toString();

        FirmaDocument doc = new FirmaDocument(firmaId, hash);
        firmaRepository.save(doc);

        return new FirmaResponse(firmaId, hash);
    }

    public FirmaResponse obtenerFirma(String id) {
        Optional<FirmaDocument> doc = firmaRepository.findById(id);
        if (doc.isPresent()) {
            FirmaDocument firma = doc.get();
            return new FirmaResponse(firma.getFirmaId(), firma.getHash());
        } else {
            throw new IllegalArgumentException("No se encontró una firma con el ID: " + id);
        }
    }

    public List<FirmaResponse> listarFirmas() {
        List<FirmaDocument> documentos = firmaRepository.findAll();
        return documentos.stream()
                .map(doc -> new FirmaResponse(doc.getFirmaId(), doc.getHash()))
                .toList();
    }

    private String generarHash(String contenidoBase64) {
        try {
            byte[] bytes = Base64.getDecoder().decode(contenidoBase64);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(bytes);
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