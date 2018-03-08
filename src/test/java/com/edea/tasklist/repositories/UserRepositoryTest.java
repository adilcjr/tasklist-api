package com.edea.tasklist.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.edea.tasklist.entities.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void testCreatetUser() {
		
		User user = new User();
		user.setEmail("adil@edea.com.br");
		user.setPassword("teste");		
		entityManager.persist(user);
		entityManager.flush();
		
		User found = userRepository.findOne(1L);		
		assertThat(found).isEqualTo(user);
		
	}
	
	@Test
	public void testFindByEmail() {
		
		User user = new User();
		user.setEmail("adil@edea.com.br");
		user.setPassword("teste");
		entityManager.persist(user);
		entityManager.flush();
		
		User found = userRepository.findByEmail("adil@edea.com.br");		
		assertThat(found).isEqualTo(user);
		
	}
}
