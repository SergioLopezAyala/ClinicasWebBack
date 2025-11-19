package com.opti_actas.clinicasweb.controllers;

import com.opti_actas.clinicasweb.dtos.ClinicaDto;
import com.opti_actas.clinicasweb.services.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinicas")
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @PostMapping
    public ResponseEntity<ClinicaDto> crear(@RequestBody ClinicaDto dto) {
        ClinicaDto creada = clinicaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDto> obtener(@PathVariable Long id) {
        return clinicaService.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClinicaDto>> listar() {
        return ResponseEntity.ok(clinicaService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaDto> actualizar(@PathVariable Long id,
                                                 @RequestBody ClinicaDto dto) {
        return clinicaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = clinicaService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
