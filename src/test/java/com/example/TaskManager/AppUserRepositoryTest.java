package com.example.TaskManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TaskManager.model.AppUser;
import com.example.TaskManager.model.AppUserRepository;

@SpringBootTest(classes = TaskManagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserRepositoryTest {

	@Autowired
	private AppUserRepository urepository;

	@Test
	public void findByUserNameShouldReturnEmail() {
		AppUser appUsers = urepository.findByUsername("user");

		assertThat(appUsers).isNotNull();
		assertThat(appUsers.getEmail()).isEqualTo("user@email.com");
	}

	@Test
	public void findByUserNameShouldReturnRole() {
		AppUser appUsers = urepository.findByUsername("admin");

		assertThat(appUsers).isNotNull();
		assertThat(appUsers.getRole()).isEqualTo("ADMIN");
	}

	@Test
	public void createNewUser() {
		AppUser newUser = new AppUser("anotherUser", "$2a$10$VCgI/lKc44ayDtDYY3Mtx.8kXUNLkSw0NeBaN4Dt4SfhZhK6s4IW6",
				"anotherUser@email.com", "USER");

		urepository.save(newUser);
		assertThat(newUser.getId()).isNotNull();
	}

	@Test
	public void deleteNewUser() {
		AppUser userToRemove = urepository.findByUsername("user");
		urepository.delete(userToRemove);

		AppUser testRemovedUser = urepository.findByUsername("user");
		assertThat(testRemovedUser).isNull();
	}

}
