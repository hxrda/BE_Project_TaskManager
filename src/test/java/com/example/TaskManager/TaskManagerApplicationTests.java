package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.web.TaskController;

@SpringBootTest
class TaskManagerApplicationTests {

	@Autowired
	private TaskController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	/*
	 * @Test void contextLoads() { }
	 */

}
