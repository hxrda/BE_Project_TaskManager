package com.example.TaskManager.model;

import jakarta.persistence.*;
	
@Entity
public class Task {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String assignment;
	private String localDateString; // CHANGE THIS!!!!
	// private int priority; //Remove
	// private double status; //Remove

	@ManyToOne
	@JoinColumn(name = "priorityId")
	private TaskPriority taskPriority;

	@ManyToOne
	@JoinColumn(name = "statusId")
	private TaskStatus taskStatus;
	
	///CHANGE date INTO THIS:////
	
	@ManyToOne
	@JoinColumn(name = "taskDateId")
	private TaskDate taskDate;


	// Constructors:
	public Task() {
	}

	public Task(String name, String email, String assignment, String localDateString, TaskPriority taskPriority, TaskStatus taskStatus) {
		super();
		this.name = name;
		this.email = email;
		this.assignment = assignment;
		this.localDateString = localDateString;
		this.taskPriority = taskPriority;
		this.taskStatus = taskStatus;
	}

	// Methods:
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getLocalDateString() {
		return localDateString;
	}

	public void setLocalDateString(String localDateString) {
		this.localDateString = localDateString;
	}

	public TaskPriority getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(TaskPriority taskPriority) {
		this.taskPriority = taskPriority;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public TaskDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(TaskDate taskDate) {
		this.taskDate = taskDate;
	}
	
	@Override
	public String toString() {
		if (this.taskPriority != null && this.taskStatus != null)
			return "Task [id=" + id + ", name=" + name + ", email=" + email + ", assignment=" + assignment + ", localDateString="
					+ localDateString + ", taskPriority=" + this.getTaskPriority() + ", taskStatus=" + this.getTaskStatus() + "]";
		else
			return "Task [id=" + id + ", name=" + name + ", email=" + email + ", assignment=" + assignment + ", localDateString="
					+ localDateString + "]";
	}

}
