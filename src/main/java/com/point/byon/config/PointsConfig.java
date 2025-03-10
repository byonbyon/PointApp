package com.point.byon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class PointsConfig {
	
	@Bean
	public OpenAPI openAPI() {
	    return new OpenAPI()
	            .info(
	                new Info()
	                .title("Point REST API 스펙")
	                .description("이 문서는 변은정이 만든 Point REST API Spec 입니다")
	                .version("1.0.0")
	                
	            );
    }
}
