package com.gse.firma.firma_api.controllers;

import com.gse.firma.firma_api.dto.FirmaRequest;
import com.gse.firma.firma_api.dto.FirmaResponse;
import com.gse.firma.firma_api.service.FirmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Firmado", description = "Operaciones para firmar archivos")
public class FirmaController {

    private final FirmaService firmaService;

    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @PostMapping("/firmar")
    @Operation(
        summary = "Firmar archivo",
        description = "Recibe un archivo en base64 y devuelve un ID único y el hash SHA-256.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Firma exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
        }
    )
    public FirmaResponse firmar(@Valid @RequestBody FirmaRequest request) {
        return firmaService.firmar(request);
    }

    @GetMapping("/infofirma/{id}")
    @Operation(
        summary = "Consultar firma",
        description = "Permite obtener los datos de una firma digital mediante su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Firma encontrada"),
            @ApiResponse(responseCode = "404", description = "Firma no encontrada")
        }
    )
    public FirmaResponse obtenerFirmaPorId(@PathVariable String id) {
        return firmaService.obtenerFirma(id);
    }

    @GetMapping("/infofirmas")
    @Operation(
        summary = "Listar firmas",
        description = "Devuelve una lista con todas las firmas almacenadas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Listado exitoso")
        }
    )
    public List<FirmaResponse> listarFirmas() {
        return firmaService.listarFirmas();
    }
}