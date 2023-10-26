package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskDateRepository extends CrudRepository<TaskDate, Long> {

	// Add methods:
	List<TaskDate> findByOrderByLocalDateAsc();

}
