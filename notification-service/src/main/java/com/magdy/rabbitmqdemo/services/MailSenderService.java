package com.magdy.rabbitmqdemo.services;


import com.magdy.rabbitmqdemo.entities.Booking;
import com.magdy.rabbitmqdemo.entities.Notification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class MailSenderService {

	@Value("${spring.mail.username}")
	private String USERNAME;

	@Value ("${spring.mail.passengername}")
	private String passengerName;
	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	public MailSenderService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
	
	public void sendHtmlMail(String to , Notification notification, Booking booking) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		// get all the data you want and put it in context
		context.setVariable("username", USERNAME);
		context.setVariable("passengername ", 1);
		String process = templateEngine.process("email_html.html", context);
		MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
		
		helper.setTo(to);
		helper.setFrom(new InternetAddress(USERNAME));
		helper.setSubject("HTML email from Triptrikker flight app");
		helper.setText(process, true);
		
		mailSender.send(message);
		
	}

	public void sendHtmlMailForDiscount(String to,String mes) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		// get all the data you want and put it in context
		context.setVariable("username", USERNAME);
		context.setVariable("discountMessage", mes);
		String process = templateEngine.process("email_html.html", context);
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.setTo(to);
		helper.setFrom(new InternetAddress(USERNAME));
		helper.setSubject("HTML email from Triptrikker flight app");
		helper.setText(process, true);

		mailSender.send(message);

	}
	
	public void sendTextMail(String to) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		Context context = new Context();
		context.setVariable("username", USERNAME);
		String process = templateEngine.process("email_text.txt", context);
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setSubject("TEXT email from Spring Boot");
		helper.setFrom(new InternetAddress(USERNAME));
		helper.setTo(to);
		helper.setText(process);
		mailSender.send(message);
	}
}
