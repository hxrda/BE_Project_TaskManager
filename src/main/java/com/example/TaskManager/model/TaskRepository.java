package com.example.TaskManager.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

	// Add methods:
	List<Task> findByName(String name);

	List<Task> findByEmail(String email);

	List<Task> findByAssignment(String assignment);
	
	List<Task> findByTaskDateDeadline(LocalDate taskDate);

	List<Task> findByOrderByTaskDateDeadlineAsc();

	List<Task> findByOrderByTaskDateDeadlineAscTaskPriorityPriorityValueAsc();

}
