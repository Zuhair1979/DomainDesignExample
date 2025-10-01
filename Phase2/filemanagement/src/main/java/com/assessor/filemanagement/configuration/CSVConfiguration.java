package com.assessor.filemanagement.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "csv")
@Profile("csv")
public record CSVConfiguration(String filePath, String separator,int columns, int cachsize) {
}
