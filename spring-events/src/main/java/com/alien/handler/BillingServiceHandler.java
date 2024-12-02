package com.alien.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alien.event.PatientDischargeEvent;

@Component
public class BillingServiceHandler {

	@Async
	@EventListener
	public void processBill(PatientDischargeEvent patientDischargeEvent) {
		// Finalize billing details
		System.out.println("Billing Service: Finalizing bill for patient " + patientDischargeEvent.getPatientId()
				+ " : " + Thread.currentThread().getName());

	}
}
