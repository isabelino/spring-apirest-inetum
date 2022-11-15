package com.formacion.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.service.ClientService;

@RestController
@RequestMapping("api")
public class ClientController {

	@Autowired
	private ClientService servicio;
	
	@GetMapping("/hola")
	public String hola() {
		return "hola apirest";
	}
	
	@GetMapping("/clientes")
	public List<Client> index(){
		
		return servicio.allClients();
		
	}
}
