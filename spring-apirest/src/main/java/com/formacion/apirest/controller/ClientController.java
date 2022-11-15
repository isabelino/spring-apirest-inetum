package com.formacion.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.service.ClientService;

@RestController
@RequestMapping("api")
public class ClientController {

	//inyeccion de dependencia del servicio
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
	
	@GetMapping("/clientes/{id}")
	public Client show(@PathVariable Long id) {
		return servicio.findClientById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client client) {
		return servicio.saveClient(client);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client,@PathVariable Long id) {
		Client clientUpdate= servicio.findClientById(id);
		
		clientUpdate.setName(client.getName());
		clientUpdate.setLast_name(client.getLast_name());
		clientUpdate.setEmail(client.getEmail());
		clientUpdate.setPhone(client.getPhone());
		clientUpdate.setCreatedAt(client.getCreatedAt());
		
		return servicio.saveClient(clientUpdate);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Client delete(@PathVariable Long id) {
		return servicio.deleteClient(id);
	}
}
