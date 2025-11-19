package com.opti_actas.clinicasweb.dtos;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DoctorDto{
        private String nombre;
        private String especialidad;
        private Long clinicaId;
        private String email;
        private String telefono;
        private Date fecha_contratacion;
}
