package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {

	// Add methods:
	List<Status> findByName(String name);

}
