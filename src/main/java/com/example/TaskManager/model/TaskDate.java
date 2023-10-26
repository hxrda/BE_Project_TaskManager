package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TaskDate {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskDateId;
	private LocalDate localDate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskDate")
	private List<Task> tasks; // List: because 1 date can have many tasks

	// Constructors:
	public TaskDate() {
	}

	public TaskDate(LocalDate localDate) {
		super();
		this.localDate = localDate;
	}

	// Methods:
	public Long getTaskDateId() {
		return taskDateId;
	}

	public void setTaskDateId(Long taskDateId) {
		this.taskDateId = taskDateId;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TaskDate [taskDateId=" + taskDateId + ", localDate=" + localDate + ", tasks=" + tasks + "]";
	}

}
