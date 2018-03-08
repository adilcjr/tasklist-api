package com.edea.tasklist.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void testFindById() {
		
		userService.findById(1L);
	}

	@Test	
	public void testListTasksByUserId() {
		
		List<Task> tasks = userService.listTasksByUserId(1L);
		tasks.forEach(task -> System.out.println(task.getTitle()));
		
	}
	
	@Test
	public void testCreateUser() {
		
		Long userId = userService.createUser("john@gmail.com", "teste");		
		User user = userService.findById(userId);		
		assertEquals("john@gmail.com", user.getEmail());
	}
	
	@Test
	public void testUpdateUser() {
		
		userService.updateUser(1L, "papai@noel.com", "papainoel");
		User user = userService.findById(1L);		
		assertEquals("papai@noel.com", user.getEmail());
		
	}
	
	@Test
	public void testDeleteUser() {
		
		Long userId = userService.createUser("john@gmail.com", "teste");
		userService.deleteUser(userId);
		assertNull(userService.findById(userId));
	}
	
	@Test
	public void testPasswordVerification() {
		
		assertFalse(userService.passwordVerification("papai@noel.com", null));
		assertFalse(userService.passwordVerification(null, "teste"));
		assertFalse(userService.passwordVerification("papai@noel.com", ""));
		assertFalse(userService.passwordVerification("", ""));
		assertFalse(userService.passwordVerification("papai@noel.com", "erro"));
		assertTrue(userService.passwordVerification("papai@noel.com", "papainoel"));
		
	}
	
	
}
