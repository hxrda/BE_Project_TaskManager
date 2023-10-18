package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PriorityRepository extends CrudRepository<Priority, Long> {

	// Add methods:
	List<Priority> findByValue(int value);

}
