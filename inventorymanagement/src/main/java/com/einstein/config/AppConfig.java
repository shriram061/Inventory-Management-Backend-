package com.einstein.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/*
 * The properties for the email server are set using the setHost(), setPort(), setUsername() 
 * and setPassword() methods of the JavaMailSenderImpl object.
 * 
 * The JavaMailSenderImpl object is returned as the JavaMailSender bean.
 */
@Configuration
public class AppConfig {

	@Value("smtp.gmail.com")
	private String host;

	@Value("587")
	private int port;

	@Value("subashriram13@gmail.com")
	private String username;

	@Value("gumaamslkztfjtlr")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}
}
