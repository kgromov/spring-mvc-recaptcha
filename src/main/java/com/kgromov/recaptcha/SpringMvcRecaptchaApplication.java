package com.kgromov.recaptcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringMvcRecaptchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcRecaptchaApplication.class, args);
	}

}
