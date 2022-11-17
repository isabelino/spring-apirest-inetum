package com.formacion.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title="APIREST - INETUM",version="1.0",description="ApiRestFull de clientes "))
public class SpringApirestApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password ="12345";
		for(int i=0;i<4;i++) {
			String passwordBcryp= passwordEncoder.encode(password);
			System.out.println(passwordBcryp);
		}
		
	}

}
