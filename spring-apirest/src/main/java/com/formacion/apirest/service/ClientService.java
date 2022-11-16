package com.formacion.apirest.service;

import java.util.List;

import com.formacion.apirest.entity.Client;
import com.formacion.apirest.entity.Region;

public interface ClientService {
	
	//metodo para mostrar todos los clientes
	public List<Client> allClients();
	
	//metodo para buscar cliente por id
	public Client findClientById(Long id);
	
	//metodo para guardar un registro de cliente
	public Client saveClient(Client client);
	
	//metodo para eliminar un registro de cliente
	public Client deleteClient(Long id);
	//metodo para buscar todas las regiones
	public List<Region> findAllRegiones();
	//metodo para buscar cliente por email
	public Client findByEmailClient(String email);

}
