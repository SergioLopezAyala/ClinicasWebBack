package com.opti_actas.clinicasweb.dtos;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ClinicaDto {
    private String nombre;
    private String direccion;
    private int cantidad_camas;
    private String telefono;
    private String ciudad;
    private Date fecha_creacion;
}
