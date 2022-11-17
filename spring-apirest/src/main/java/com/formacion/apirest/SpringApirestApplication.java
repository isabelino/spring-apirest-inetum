package com.formacion.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title="APIREST - INETUM",version="1.0",description="ApiRestFull de clientes "))
public class SpringApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApirestApplication.class, args);
	}

}
