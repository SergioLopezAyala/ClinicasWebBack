package com.opti_actas.clinicasweb.repos;

import com.opti_actas.clinicasweb.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Long> {
}
