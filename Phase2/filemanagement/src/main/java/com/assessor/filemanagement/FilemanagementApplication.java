package com.assessor.filemanagement;

import com.assessor.filemanagement.configuration.CSVConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CSVConfiguration.class)
public class FilemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilemanagementApplication.class, args);
	}

}
