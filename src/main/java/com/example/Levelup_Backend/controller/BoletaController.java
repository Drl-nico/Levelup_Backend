package com.example.Levelup_Backend.controller;

import com.example.Levelup_Backend.Dto.BoletaRequestDTO;
import com.example.Levelup_Backend.model.Boleta;
import com.example.Levelup_Backend.service.BoletaService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Boletas", description = "Operaciones de creación y consulta de boletas de compra")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @Hidden
    @PostMapping
    public ResponseEntity<?> crearBoleta(@RequestBody BoletaRequestDTO dto, Principal principal) {
        try {
            String username = principal != null ? principal.getName() : null;
            Boleta b = boletaService.crearBoleta(dto, username);
            return ResponseEntity.ok(b);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Collections.singletonMap("message", ex.getReason()));
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprimir error en consola para debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error interno: " + ex.getMessage()));
        }
    }

    @Operation(summary = "Listar boletas", description = "Obtiene el historial de boletas generadas")
    @GetMapping
    public List<Boleta> obtenerBoletas() {
        return boletaService.obtenerBoletas();
    }
}