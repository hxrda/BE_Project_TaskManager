package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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
import com.example.TaskManager.model.TaskDate;
import com.example.TaskManager.model.TaskDateRepository;
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
	
	@Autowired
	private TaskDateRepository drepository;

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
		
		LocalDate deadline = LocalDate.parse("2023-10-10");
		TaskDate taskDate = new TaskDate (deadline);
		drepository.save(taskDate);

		Task task = new Task("Mary Shelley", "ms@email.com", "Write novel", taskDate, taskPriority, taskStatus);
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
