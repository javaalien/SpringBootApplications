package com.alien.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.model.EmailAddress;
import com.alien.model.EmailRequest;
import com.alien.validator.EmailAddressForCustomValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emails")
@Validated
public class EmailController {

	@PostMapping("/send")
	public ResponseEntity<String> sendEmails(@Valid @RequestBody List<@Valid EmailAddress> request,
			BindingResult result) {
		return ResponseEntity.ok("Emails sent successfully");
	}

	@PostMapping("/sendCustom")
	public ResponseEntity<String> sendEmailsCustom(
			@Valid @RequestBody List<@Valid EmailAddressForCustomValidator> request, BindingResult result) {
		return ResponseEntity.ok("Emails sent successfully");
	}

	@PostMapping("/sendObject")
	public ResponseEntity<String> sendEmails1(@Valid @RequestBody EmailRequest request, BindingResult result) {
		return ResponseEntity.ok("Emails sent successfully");
	}
}
