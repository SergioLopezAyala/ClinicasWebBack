package com.opti_actas.clinicasweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Table
@Entity
public class Clinica {
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private int cantidad_camas;
    @Column
    private String telefono;
    @Column
    private String ciudad;
    @Column
    private Date fecha_creacion;
}
