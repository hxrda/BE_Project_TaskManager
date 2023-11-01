package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class TaskPriority {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priorityId;
	private String priorityValue;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskPriority")
	private List<Task> tasks; // List: because 1 priority category can have many task

	// Constructors:
	public TaskPriority() {
	}

	public TaskPriority(String priorityValue) {
		super();
		this.priorityValue = priorityValue;
	}

	// Methods:
	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityValue() {
		return priorityValue;
	}

	public void setPriorityValue(String priorityValue) {
		this.priorityValue = priorityValue;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Priority [priorityId=" + priorityId + ", value=" + priorityValue + ", tasks=" + tasks + "]";
	}

}
