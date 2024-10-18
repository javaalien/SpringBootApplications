package com.alien.dto;

import com.alien.entity.Appointment;
import com.alien.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAppointmentRequest {

	private Patient patient;

	private Appointment appointment;

}
