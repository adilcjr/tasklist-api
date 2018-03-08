package com.edea.tasklist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.entities.User;
import com.edea.tasklist.repositories.UserRepository;
import com.edea.tasklist.utils.Encryptor;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findById(Long userId) {
		
		if (userId != null) {
			return userRepository.findOne(userId);
		} else {
			return null;
		}
	}

	public List<Task> listTasksByUserId(Long userId) {

		if (userId != null) {
			User user = userRepository.findOne(userId);
			if (user != null) {
				List<Task> tasks = user.getTasks();
				//TODO REMOVE DELETED TASKS
				// The user could exist but doesn't have tasks assigned
				if (tasks != null) {
					return tasks;
				}
			}
		}
		return null;
	}
	

	public Long createUser(String email, String password) {
		
		if (email != null && password != null) {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			userRepository.save(user);
			return user.getId();
		}
		return null;
	}

	public boolean updateUser(Long userId, String email, String password) {
		
		User user = findById(userId);
		
		if (user != null) {
			if (email != null) user.setEmail(email);
			if (password != null) user.setPassword(password);
			userRepository.save(user);			
			return true;
		}
		return false;
	}

	public boolean deleteUser(Long userId) {
		
		if (userRepository.exists(userId)) { 
			userRepository.delete(userId);
			return true;
		}
		return false;
	}

	public boolean passwordVerification(String email, String password) {
		
		if (email!= null && password != null) {
			User user = userRepository.findByEmail(email);
			if (user!= null && user.getPassword().equals(
					Encryptor.getDataEncrypted(password)))
				return true;
		}
		return false;
	}
}