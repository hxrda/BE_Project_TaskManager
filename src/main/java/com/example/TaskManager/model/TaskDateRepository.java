package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskDateRepository extends CrudRepository<TaskDate, Long> {

	// Add methods:
	List<TaskDate> findByOrderByDeadlineAsc();
	
	List<TaskDate> findByDeadline(LocalDate deadline);
	
	//?
	Optional<TaskDate> findById (Long taskDateId);

}
