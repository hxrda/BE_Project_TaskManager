package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.Status;
import com.example.TaskManager.model.StatusRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatusRepositoryTest {

	@Autowired
	private StatusRepository srepository;

	@Test
	public void findByNameShouldReturnExistingStatusCategory() {
		List<Status> statuses = srepository.findByName("Reschedule");

		assertThat(statuses).hasSize(1);
	}

	@Test
	public void createNewStatus() {
		Status status = new Status("Rejected");
		srepository.save(status);
		
		assertThat(status.getStatusId()).isNotNull();
	}

	@Test
	public void deleteStatus() {
		List<Status> statuses = srepository.findByName("Completed");
		Status status = statuses.get(0);
		srepository.delete(status);

		List<Status> newStatuses = srepository.findByName("Completed");
		assertThat(newStatuses).hasSize(0);
	}

}
