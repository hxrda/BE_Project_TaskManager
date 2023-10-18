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
	private String task;
	private String localDateString; // CHANGE THIS!!!!
	// private int priority; //Remove
	// private double status; //Remove

	@ManyToOne
	@JoinColumn(name = "priorityId")
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;

	// Constructors:
	public Task() {
	}

	public Task(String name, String email, String task, String localDateString, Priority priority, Status status) {
		super();
		this.name = name;
		this.email = email;
		this.task = task;
		this.localDateString = localDateString;
		this.priority = priority;
		this.status = status;
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getLocalDateString() {
		return localDateString;
	}

	public void setLocalDateString(String localDateString) {
		this.localDateString = localDateString;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		if (this.priority != null && this.status != null)
			return "Task [id=" + id + ", name=" + name + ", email=" + email + ", task=" + task + ", localDateString="
					+ localDateString + ", priority=" + this.getPriority() + ", status=" + this.getStatus() + "]";
		else
			return "Task [id=" + id + ", name=" + name + ", email=" + email + ", task=" + task + ", localDateString="
					+ localDateString + "]";
	}

}
