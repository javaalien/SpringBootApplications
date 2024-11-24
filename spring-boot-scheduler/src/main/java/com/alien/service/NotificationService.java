package com.alien.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {

	@Autowired
	private ReportService service;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${report.send.email.toList}")
	private String toEMails;

	public String sendDailyReports() throws MessagingException, IOException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom(sender);
		helper.setTo(toEMails.split(","));
		helper.setSubject("List Of Orders_" + new Date().getTime());
		helper.setText("Hi User\\nPlease find the attachment for today's order recieved !");

		byte[] report = service.generateReport();

		ByteArrayResource content = new ByteArrayResource(report);

		helper.addAttachment(new Date().getTime() + "_orders.xlsx", content);

		javaMailSender.send(mimeMessage);

		return "Mail sent successfully with attachment ";
	}
	
	

}
