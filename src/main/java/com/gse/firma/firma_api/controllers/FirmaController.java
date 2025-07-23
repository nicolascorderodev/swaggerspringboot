package com.gse.firma.firma_api.controllers;

import com.gse.firma.firma_api.dto.FirmaRequest;
import com.gse.firma.firma_api.dto.FirmaResponse;
import com.gse.firma.firma_api.service.FirmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firmas")
@Tag(name = "Firmado", description = "Operaciones para firmar archivos")
public class FirmaController {

    private final FirmaService firmaService;

    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @PostMapping
    @Operation(
        summary = "Firmar archivo",
        description = "Recibe un archivo en base64 y devuelve un ID único y el hash SHA-256.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Firma exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
        }
    )
    public FirmaResponse firmar(@RequestBody FirmaRequest request) {
        return firmaService.firmar(request);
    }
    @GetMapping("/{id}")
    @Operation(
        summary = "Consultar firma",
        description = "Permite obtener los datos de una firma digital mediante su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Firma encontrada"),
            @ApiResponse(responseCode = "404", description = "Firma no encontrada")
        }
    )
    public FirmaResponse obtenerFirmaPorId(
        @PathVariable String id
    ){
    return firmaService.obtenerFirma(id);
}}