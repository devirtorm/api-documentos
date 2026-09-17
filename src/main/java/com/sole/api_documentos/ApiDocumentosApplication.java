package com.sole.api_documentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiDocumentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDocumentosApplication.class, args);
	}

}
