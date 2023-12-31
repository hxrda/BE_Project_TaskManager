package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StatusRepository extends CrudRepository<TaskStatus, Long> {

	// Add methods:
	List<TaskStatus> findByStatusName(String statusName);

}
