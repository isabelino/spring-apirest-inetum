package com.formacion.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	//inyeccion de dependencia desde mi repositorio
	@Autowired
	private ClientRepository repositorio;
	
	@Override
	public List<Client> allClients() {
		return (List<Client>) repositorio.findAll();
	}

}
