package com.opti_actas.clinicasweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Table
@Entity
@RequiredArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String especialidad;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Clinica clinica;
    @Column
    private String email;
    @Column
    private String telefono;
    @Column
    private Date fecha_contratacion;

}
