package com.alien.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
