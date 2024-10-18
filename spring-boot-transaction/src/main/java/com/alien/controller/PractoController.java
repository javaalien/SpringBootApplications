package com.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.PatientAppointmentRequest;
import com.alien.service.PractoService;

@RestController
@RequestMapping("/practo")
public class PractoController {

	@Autowired
	private PractoService service;

	@PostMapping("/bookappointment")
	public String bookDoctorsAppointment(@RequestBody PatientAppointmentRequest request) {
		return service.bookAppointment(request);
	}

}
