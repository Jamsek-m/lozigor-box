package com.mjamsek.lozigorbox;

import com.mjamsek.lozigorbox.services.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class LozigorBoxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LozigorBoxApplication.class, args);
		context.getBean(FileService.class).initStorage();
	}
}
