package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Status {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statusId;
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Task> tasks; // List: because 1 status category can have many task

	// Constructors:
	public Status() {
	}

	public Status(String name) {
		super();
		this.name = name;
	}

	// Methods:
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", name=" + name + ", tasks=" + tasks + "]";
	}

}
