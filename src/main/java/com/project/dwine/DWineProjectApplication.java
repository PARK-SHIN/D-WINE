package com.project.dwine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.dwine")
public class DWineProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DWineProjectApplication.class, args);
	}

}
