package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.TaskPriority;
import com.example.TaskManager.model.PriorityRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriorityRepositoryTest {

	@Autowired
	private PriorityRepository prepository;

	@Test
	public void findByValueShouldReturnExistingPriorityCategory() {
		List<TaskPriority> priorities = prepository.findByPriorityValue("3");

		assertThat(priorities).hasSize(1);
	}

	@Test
	public void createNewPriority() {
		TaskPriority priority = new TaskPriority("4");
		prepository.save(priority);

		assertThat(priority.getPriorityId()).isNotNull();
	}

	@Test
	public void deletePriority() {
		List<TaskPriority> priorities = prepository.findByPriorityValue("3");
		TaskPriority priority = priorities.get(0);
		prepository.delete(priority);

		List<TaskPriority> updatedPriorities = prepository.findByPriorityValue("3");
		assertThat(updatedPriorities).hasSize(0);
	}

}
