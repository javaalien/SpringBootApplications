package com.alien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alien.dto.PatientAppointmentRequest;
import com.alien.entity.Appointment;
import com.alien.entity.Patient;
import com.alien.respository.AppointmentRepository;
import com.alien.respository.PatientRepository;
import com.alien.util.PromocodeValidator;

@Service
public class PractoService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional
	public String bookAppointment(PatientAppointmentRequest request) {
		Patient patient = request.getPatient();
		long patientId = patientRepository.save(patient).getPatientId();

		Appointment appointment = request.getAppointment();

		if (appointment.getPromoCode() != null) {
			PromocodeValidator.validatePromoCode(appointment.getPromoCode());
		}
		appointment.setPatientId(patientId);

		String appintmentNo = appointmentRepository.save(appointment).getAppointmentId();

		return "Hi " + patient.getName() + " Your appointment booked successfully & appointment number is : "
				+ appintmentNo;
	}
	
	
}
