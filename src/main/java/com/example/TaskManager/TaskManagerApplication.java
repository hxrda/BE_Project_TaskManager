package com.example.TaskManager;

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
import com.example.TaskManager.model.TaskRepository;

@SpringBootApplication
public class TaskManagerApplication {

	private static final Logger log = LoggerFactory.getLogger(TaskManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	/* >>> Entity field information <<< */
	// Book fields: (id), name, email, assignment, localDateString
	// Priority fields: (priorityId), priorityValue (1-3)
	// Status fields: (statusId), statusName

	@Bean
	public CommandLineRunner taskDemo(TaskRepository trepository, PriorityRepository prepository,
			StatusRepository srepository, AppUserRepository urepository) {
		return (args) -> {
			// Alternatively: syso

			log.info("save a couple of priority values");
			prepository.save(new TaskPriority("1"));
			prepository.save(new TaskPriority("2"));
			prepository.save(new TaskPriority("3"));

			log.info("save a couple of statuses");
			srepository.save(new TaskStatus("Pending"));
			srepository.save(new TaskStatus("Completed"));
			srepository.save(new TaskStatus("Reschedule"));

			log.info("save a couple of tasks");
			trepository.save(new Task("John Doe", "jdoe@email.com", "Pay taxes", "2023-10-30",
					prepository.findByPriorityValue("1").get(0), srepository.findByStatusName("Pending").get(0)));
			trepository.save(new Task("Mary Sue", "msue@email.com", "Book trip", "2024-01-12",
					prepository.findByPriorityValue("3").get(0), srepository.findByStatusName("Reschedule").get(0)));
			trepository.save(new Task("Ellie Musk", "mmusk@email.com", "Science project", "2023-11-29",
					prepository.findByPriorityValue("2").get(0), srepository.findByStatusName("Pending").get(0)));
			trepository.save(new Task("Elton Musk", "emusk@email.com", "Violin competition", "2022-11-22",
					prepository.findByPriorityValue("1").get(0), srepository.findByStatusName("Completed").get(0)));

			// Create users: admin/admin, user/user
			log.info("create a couple of users");
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@email.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			// These cause errors for some reason:
			/*
			 * log.info("fetch all tasks"); for (Task task : trepository.findAll()) {
			 * log.info(task.toString()); } log.info("fetch all tasks by name"); for (Task
			 * task : trepository.findByName("name")) { log.info(task.toString()); }
			 */

		};
	}

}
