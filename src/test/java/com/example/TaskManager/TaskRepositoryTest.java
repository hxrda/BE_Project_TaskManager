package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.TaskPriority;
import com.example.TaskManager.model.PriorityRepository;
import com.example.TaskManager.model.TaskStatus;
import com.example.TaskManager.model.StatusRepository;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.model.TaskRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private PriorityRepository prepository;

	@Autowired
	private StatusRepository srepository;

	@Test
	public void findByNameShouldReturnEmail() {
		List<Task> tasks = repository.findByName("John Doe");

		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getEmail()).isEqualTo("jdoe@email.com");
	}

	@Test
	public void findByNameShouldReturnAssignment() {
		List<Task> tasks = repository.findByName("Ellie Musk");

		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getAssignment()).isEqualTo("Science project");
	}

	@Test
	public void findByEmailShouldReturnName() {
		List<Task> tasks = repository.findByEmail("msue@email.com");

		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getName()).isEqualTo("Mary Sue");
	}

	@Test
	public void createNewTask() {
		TaskPriority taskPriority = new TaskPriority("2");
		prepository.save(taskPriority);

		TaskStatus taskStatus = new TaskStatus("Reschedule");
		srepository.save(taskStatus);

		Task task = new Task("Mary Shelley", "ms@email.com", "Write novel", "1818-10-17", taskPriority, taskStatus);
		repository.save(task);
		assertThat(task.getId()).isNotNull();
	}

	@Test
	public void deleteTask() {
		List<Task> tasks = repository.findByName("John Doe");
		Task task = tasks.get(0);
		repository.delete(task);
		
		List<Task> newTasks = repository.findByName("John Doe");
		assertThat(newTasks).hasSize(0);
	}

}
