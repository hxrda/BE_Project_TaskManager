package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.TaskStatus;
import com.example.TaskManager.model.StatusRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatusRepositoryTest {

	@Autowired
	private StatusRepository srepository;

	@Test
	public void findByNameShouldReturnExistingStatusCategory() {
		List<TaskStatus> statuses = srepository.findByStatusName("Reschedule");

		assertThat(statuses).hasSize(1);
	}

	@Test
	public void createNewStatus() {
		TaskStatus status = new TaskStatus("Rejected");
		srepository.save(status);
		
		assertThat(status.getStatusId()).isNotNull();
	}

	@Test
	public void deleteStatus() {
		List<TaskStatus> statuses = srepository.findByStatusName("Completed");
		TaskStatus status = statuses.get(0);
		srepository.delete(status);

		List<TaskStatus> newStatuses = srepository.findByStatusName("Completed");
		assertThat(newStatuses).hasSize(0);
	}

}
