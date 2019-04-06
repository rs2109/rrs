package com.tech.bahera;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tech.bahera.fileUpload.StorageService;

@SpringBootApplication
public class RrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RrsApplication.class, args);
	}
	
}
