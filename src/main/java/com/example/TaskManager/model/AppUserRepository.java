package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	
	//Add methods:
	AppUser findByUsername(String username);

}
