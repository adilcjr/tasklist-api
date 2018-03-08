package com.edea.tasklist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edea.tasklist.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

}
