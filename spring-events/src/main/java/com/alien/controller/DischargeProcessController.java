package com.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.PatientDischargeRequest;
import com.alien.service.PatientDischargeService;

@RestController
@RequestMapping("/discharge/api")
public class DischargeProcessController {

	@Autowired
	private PatientDischargeService patientDischargeService;

	@PostMapping("/process")
	public String dischargePatient(@RequestBody PatientDischargeRequest request) {

		return patientDischargeService.dischargePatient(request.getPatientId(), request.getPatientName());

	}

}
