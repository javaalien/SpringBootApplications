package com.alien.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
