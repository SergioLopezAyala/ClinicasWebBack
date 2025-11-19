package com.opti_actas.clinicasweb.services;

import com.opti_actas.clinicasweb.dtos.ClinicaDto;
import com.opti_actas.clinicasweb.models.Clinica;
import com.opti_actas.clinicasweb.repos.ClinicaRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicaService {

    private final ClinicaRepo clinicaRepo;
    private final ModelMapper mapper = new ModelMapper();

    @Transactional
    public ClinicaDto crear(ClinicaDto dto) {
        Clinica entity = mapper.map(dto, Clinica.class);
        entity.setId(null);
        return mapper.map(clinicaRepo.save(entity), ClinicaDto.class);
    }

    @Transactional
    public Optional<ClinicaDto> obtener(Long id) {
        return clinicaRepo.findById(id)
                .map(c -> mapper.map(c, ClinicaDto.class));
    }

    @Transactional
    public List<ClinicaDto> listar() {
        List<ClinicaDto> out = new ArrayList<>();
        for (Clinica c : clinicaRepo.findAll()) {
            out.add(mapper.map(c, ClinicaDto.class));
        }
        return out;
    }

    @Transactional
    public Optional<ClinicaDto> actualizar(Long id, ClinicaDto dto) {
        return clinicaRepo.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setDireccion(dto.getDireccion());
            existing.setCantidad_camas(dto.getCantidad_camas());
            existing.setTelefono(dto.getTelefono());
            existing.setCiudad(dto.getCiudad());
            existing.setFecha_creacion(dto.getFecha_creacion());

            Clinica actualizada = clinicaRepo.save(existing);
            return mapper.map(actualizada, ClinicaDto.class);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        return clinicaRepo.findById(id).map(clinica -> {
            clinicaRepo.delete(clinica);
            return true;
        }).orElse(false);
    }
}
