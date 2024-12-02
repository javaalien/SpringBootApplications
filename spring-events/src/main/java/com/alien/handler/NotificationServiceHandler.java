package com.alien.handler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alien.event.PatientDischargeEvent;

@Component
public class NotificationServiceHandler {

	@Async
	@EventListener
	public void notifyPatients(PatientDischargeEvent event) {
		// Send discharge notification
		System.out.println("Notification Service: Sending discharge notification for patient " + event.getPatientName()
				+ " : " + Thread.currentThread().getName());
	}

}
