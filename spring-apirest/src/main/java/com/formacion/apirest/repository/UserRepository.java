package com.formacion.apirest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.apirest.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	
	public User findByUsername(String username);
	
	@Query("select u from User u where u.username=?1")
	public User findByUsername2(String username);

}
