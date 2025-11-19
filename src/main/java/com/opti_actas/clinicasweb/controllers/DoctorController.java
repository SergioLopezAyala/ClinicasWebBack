package com.opti_actas.clinicasweb.controllers;

import com.opti_actas.clinicasweb.dtos.DoctorDto;
import com.opti_actas.clinicasweb.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctores")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> crear(@RequestBody DoctorDto dto) {
        DoctorDto creado = doctorService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> obtener(@PathVariable Long id) {
        return doctorService.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> listar() {
        return ResponseEntity.ok(doctorService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> actualizar(@PathVariable Long id,
                                                @RequestBody DoctorDto dto) {
        return doctorService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = doctorService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }
}
