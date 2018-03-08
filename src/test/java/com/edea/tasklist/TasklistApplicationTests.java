package com.edea.tasklist;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.entities.User;
import com.edea.tasklist.repositories.TaskRespository;
import com.edea.tasklist.repositories.UserRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class TasklistApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRespository taskRepository;

	@Test
	public void testCreatetUser() {
		
		User user = new User();
		user.setEmail("adil@edea.com.br");
		user.setPassword("teste");
		
		userRepository.save(user);
	}
	
	@Test
	public void testCreateTask() {
		
		Task task = new Task();
		task.setTitle("My Second Task");
		task.setDescription("I need to clean my bedroom.");
		task.setCreationDate(new Date());
		
		User user = userRepository.findOne(1L);
		task.setUser(user);
		
		taskRepository.save(task);
	}
}
