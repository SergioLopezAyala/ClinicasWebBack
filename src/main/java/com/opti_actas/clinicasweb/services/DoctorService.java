package com.opti_actas.clinicasweb.services;


import com.opti_actas.clinicasweb.dtos.DoctorDto;
import com.opti_actas.clinicasweb.models.Clinica;
import com.opti_actas.clinicasweb.models.Doctor;
import com.opti_actas.clinicasweb.repos.DoctorRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepo doctorRepo;
    private final ModelMapper mapper = new ModelMapper();
    private final ClinicaService clinicaService;

    @Transactional
    public DoctorDto crear(DoctorDto doctorDto){
        Doctor entity = mapper.map(doctorDto, Doctor.class);
        entity.setId(null);
        return mapper.map(doctorRepo.save(entity), DoctorDto.class);
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<DoctorDto> obtener(Long id) {
        return doctorRepo.findById(id)
                .map(e -> mapper.map(e, DoctorDto.class));
    }

    @Transactional(rollbackOn = Exception.class)
    public List<DoctorDto> listar() {
        List<DoctorDto> out = new ArrayList<>();
        for (Doctor e : doctorRepo.findAll()) {
            out.add(mapper.map(e, DoctorDto.class));
        }
        return out;
    }

    @Transactional
    public Optional<DoctorDto> actualizar(Long id, DoctorDto dto) {
        return doctorRepo.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setEspecialidad(dto.getEspecialidad());

            Clinica clinica = mapper.map(clinicaService
                    .obtener(dto.getClinicaId())
                    .orElseThrow(
                            () -> new IllegalArgumentException(
                                    "Ahi no master"
                            )
                    ),Clinica.class);
            existing.setClinica(clinica);
            existing.setEmail(dto.getEmail());
            existing.setTelefono(dto.getTelefono());
            existing.setFecha_contratacion(dto.getFecha_contratacion());
            Doctor actualizado = doctorRepo.save(existing);
            return mapper.map(actualizado, DoctorDto.class);
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        return doctorRepo.findById(id).map(doc -> {
            doctorRepo.delete(doc);
            return true;
        }).orElse(false);
    }
}
