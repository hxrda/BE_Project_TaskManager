package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TaskDate {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskDateId;
	private LocalDate deadline;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskDate")
	private List<Task> tasks;

	// Constructors:
	public TaskDate() {
	}

	public TaskDate(LocalDate deadline) {
		super();
		this.deadline = deadline;
	}

	public TaskDate(String stringDate) {
		super();
		this.deadline = LocalDate.parse(stringDate);
	}

	// Methods:
	public Long getTaskDateId() {
		return taskDateId;
	}

	public void setTaskDateId(Long taskDateId) {
		this.taskDateId = taskDateId;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TaskDate [taskDateId=" + taskDateId + ", deadline=" + deadline + ", tasks=" + tasks + "]";
	}

}
