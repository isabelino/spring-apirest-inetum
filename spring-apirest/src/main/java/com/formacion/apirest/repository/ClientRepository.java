package com.formacion.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacion.apirest.entity.Client;
import com.formacion.apirest.entity.Region;
import com.formacion.apirest.entity.User;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{

	//metodo para buscar cliente por email
	public Client findByEmail(String email);

	//metodo para buscar todas las regiones
	@Query("from Region")
	public List<Region> findAllRegions();
	
	
	
}
