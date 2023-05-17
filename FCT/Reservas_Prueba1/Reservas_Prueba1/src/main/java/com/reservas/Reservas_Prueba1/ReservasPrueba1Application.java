package com.reservas.Reservas_Prueba1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class ReservasPrueba1Application {

	public static void main(String[] args) {
		SpringApplication.run(ReservasPrueba1Application.class, args);
	}

	@Bean
	public JavaMailSender getJavaMAilSender(){

		JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("casarurallosmaragatos@gmail.com");
		javaMailSender.setPassword("juazruetkyebqtjl");

		Properties properties=javaMailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");


			return javaMailSender;
	}
}
