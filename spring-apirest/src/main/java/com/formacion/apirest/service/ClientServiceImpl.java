package com.formacion.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	//inyeccion de dependencia desde mi repositorio
	@Autowired
	private ClientRepository repositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> allClients() {
		return (List<Client>) repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findClientById(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Client saveClient(Client client) {
		return repositorio.save(client);
	}

	@Override
	@Transactional
	public Client deleteClient(Long id) {
		Client client = new Client();
		client=findClientById(id);
		repositorio.deleteById(id);
		
		return client;
	}

	

}
