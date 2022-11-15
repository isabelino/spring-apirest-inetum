package com.formacion.apirest.service;

import java.util.List;

import com.formacion.apirest.entity.Client;

public interface ClientService {
	
	//metodo para mostrar todos los clientes
	public List<Client> allClients();
	
	//metodo para buscar cliente por id
	public Client findClientById(Long id);
	
	//metodo para guardar un registro de cliente
	public Client saveClient(Client client);
	
	//metodo para eliminar un registro de cliente
	public Client deleteClient(Long id);

}
