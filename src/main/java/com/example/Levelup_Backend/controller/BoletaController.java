package com.example.Levelup_Backend.controller;

import com.example.Levelup_Backend.Dto.BoletaRequestDTO;
import com.example.Levelup_Backend.model.Boleta;
import com.example.Levelup_Backend.service.BoletaService;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/boletas")
@CrossOrigin(origins = "http://localhost:3000")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;
    @Hidden
    @PostMapping
    public Boleta crearBoleta(@RequestBody BoletaRequestDTO dto) {
        return boletaService.crearBoleta(dto);
    }
    @GetMapping
    public java.util.List<Boleta> obtenerBoletas() {
        return boletaService.obtenerBoletas();
}
}