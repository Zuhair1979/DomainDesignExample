package com.filemanagement.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator PeronMgmRout(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/csv/persons/**")
						.filters( f -> f
								.stripPrefix(1)
						)

						.uri("http://localhost:8080"))
				.route(r -> r
						.path("/postgres/db/persons/**")
						.filters(f -> f.stripPrefix(1)) // remove /postgres
						.uri("http://localhost:8080"))
				.build();

	}

}
