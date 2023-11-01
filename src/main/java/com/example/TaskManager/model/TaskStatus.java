package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class TaskStatus {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statusId;
	private String statusName;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskStatus")
	private List<Task> tasks; // List: because 1 status category can have many tasks

	// Constructors:
	public TaskStatus() {
	}

	public TaskStatus(String statusName) {
		super();
		this.statusName = statusName;
	}

	// Methods:
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + ", tasks=" + tasks + "]";
	}

}
