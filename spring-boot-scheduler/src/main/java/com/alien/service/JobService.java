package com.alien.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class JobService {

	@Autowired
	private NotificationService service;

	@Scheduled(cron = "${cron_interval}", zone = "IST")
	public void process() {
		System.out.println("job Started on " + new Date());

		try {
			service.sendDailyReports();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
