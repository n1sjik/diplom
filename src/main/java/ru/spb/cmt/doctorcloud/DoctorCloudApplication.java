package ru.spb.cmt.doctorcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DoctorCloudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DoctorCloudApplication.class, args);
	}

}
