package com.edea.tasklist.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.entities.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRespositoryTest {
	
	@Autowired
	TaskRespository taskRepository;
	
	@Autowired
	TestEntityManager entityManager;

	@Test
	public void testCreateTask() {
		
		User user = new User();
		user.setEmail("adil@edea.com.br");
		user.setPassword("password");
		
		entityManager.persist(user);
		entityManager.flush();
		
		Task task = new Task();
		task.setTitle("My First Task");
		task.setDescription("I need to wash the dishes");
		task.setCreationDate(new Date());		
		task.setUser(user);
		
		entityManager.persist(task);
		entityManager.flush();
		
		Task found = taskRepository.findOne(1L);
		assertThat(found).isEqualTo(task);
		
	}

}
