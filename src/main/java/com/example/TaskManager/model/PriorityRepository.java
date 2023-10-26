package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PriorityRepository extends CrudRepository<TaskPriority, Long> {

	// Add methods:
	List<TaskPriority> findByPriorityValue(String priorityValue);

}
