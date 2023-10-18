package com.example.TaskManager.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Priority {
	// Fields:
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priorityId;
	private String value;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priority")
	private List<Task> tasks; // List: because 1 priority category can have many task

	// Constructors:
	public Priority() {
	}

	public Priority(String value) {
		super();
		this.value = value;
	}

	// Methods:
	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Priority [priorityId=" + priorityId + ", value=" + value + ", tasks=" + tasks + "]";
	}

}
