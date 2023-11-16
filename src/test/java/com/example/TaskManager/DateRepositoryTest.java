package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.TaskDate;
import com.example.TaskManager.model.TaskDateRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DateRepositoryTest {
	/*

	@Autowired
	private TaskDateRepository drepository;

	@Test
	public void findByDateShouldReturnExistingDeadline() {
		List<TaskDate> deadlines = drepository.findByDeadline(LocalDate.parse("2023-12-15"));

		assertThat(deadlines).hasSize(1);
	}

	@Test
	public void createNewDeadline() {
		TaskDate deadline = new TaskDate(LocalDate.parse("2024-11-11"));
		drepository.save(deadline);

		assertThat(deadline.getTaskDateId()).isNotNull();
	}

	@Test
	public void deleteDeadline() {
		List<TaskDate> deadlines = drepository.findByDeadline(LocalDate.parse("2023-11-30"));
		TaskDate deadline = deadlines.get(0);
		drepository.delete(deadline);

		List<TaskDate> updatedDeadlines = drepository.findByDeadline(LocalDate.parse("2023-11-30"));
		assertThat(updatedDeadlines).hasSize(0);
	}
    */
}
