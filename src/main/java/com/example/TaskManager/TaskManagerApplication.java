package com.example.TaskManager;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.TaskManager.model.AppUser;
import com.example.TaskManager.model.AppUserRepository;
import com.example.TaskManager.model.TaskPriority;
import com.example.TaskManager.model.PriorityRepository;
import com.example.TaskManager.model.TaskStatus;
import com.example.TaskManager.model.StatusRepository;
import com.example.TaskManager.model.Task;
import com.example.TaskManager.model.TaskDate;
import com.example.TaskManager.model.TaskDateRepository;
import com.example.TaskManager.model.TaskRepository;

@SpringBootApplication
public class TaskManagerApplication {

	private static final Logger log = LoggerFactory.getLogger(TaskManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	/* >>> Entity field information <<< */
	// Task fields: (id), name, email, assignment
	// Priority fields: (priorityId), priorityValue (1-3)
	// Status fields: (statusId), statusName
	// Date fields: (taskDateId), deadline

	@Bean
	public CommandLineRunner taskDemo(TaskRepository trepository, PriorityRepository prepository,
			StatusRepository srepository, AppUserRepository urepository, TaskDateRepository drepository) {
		return (args) -> {

			// Create priorities:
			log.info("save a couple of priority values");
			prepository.save(new TaskPriority("1"));
			prepository.save(new TaskPriority("2"));
			prepository.save(new TaskPriority("3"));

			// Create statuses:
			log.info("save a couple of statuses");
			srepository.save(new TaskStatus("Pending"));
			srepository.save(new TaskStatus("Completed"));
			srepository.save(new TaskStatus("Reschedule"));

			// Create dates/deadlines:
			log.info("save a couple of dates");
			LocalDate taskDate1 = LocalDate.parse("2023-12-15");
			LocalDate taskDate2 = LocalDate.parse("2024-01-02");
			LocalDate taskDate3 = LocalDate.parse("2023-11-30");
			LocalDate taskDate4 = LocalDate.parse("2023-12-16");

			drepository.save(new TaskDate(taskDate1));
			drepository.save(new TaskDate(taskDate2));
			drepository.save(new TaskDate(taskDate3));
			drepository.save(new TaskDate(taskDate4));

			// Create tasks:
			log.info("save a couple of tasks");
			trepository.save(new Task("John Doe", "jdoe@email.com", "Pay taxes",
					drepository.findByDeadline(taskDate1).get(0), prepository.findByPriorityValue("1").get(0),
					srepository.findByStatusName("Pending").get(0)));

			trepository.save(new Task("Mary Sue", "msue@email.com", "Book trip",
					drepository.findByDeadline(taskDate2).get(0), prepository.findByPriorityValue("3").get(0),
					srepository.findByStatusName("Reschedule").get(0)));

			trepository.save(new Task("Ellie Musk", "mmusk@email.com", "Science project",
					drepository.findByDeadline(taskDate3).get(0), prepository.findByPriorityValue("2").get(0),
					srepository.findByStatusName("Pending").get(0)));

			trepository.save(new Task("Elton Musk", "emusk@email.com", "Violin competition",
					drepository.findByDeadline(taskDate4).get(0), prepository.findByPriorityValue("1").get(0),
					srepository.findByStatusName("Completed").get(0)));

			// Create users: admin/admin, user/user
			log.info("create a couple of users");
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@email.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

		};
	}

}
