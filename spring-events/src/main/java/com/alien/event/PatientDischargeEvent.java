package com.alien.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDischargeEvent extends ApplicationEvent {

	private String patientId;
	private String patientName;

	public PatientDischargeEvent(Object source, String patientId, String patientName) {
		super(source);
		this.patientId = patientId;
		this.patientName = patientName;
	}
}
