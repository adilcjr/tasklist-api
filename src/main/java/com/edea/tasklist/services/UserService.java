package com.edea.tasklist.services;

import java.util.List;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.entities.User;

/**
 * The User services. All operations for the users entity. 
 * 
 * @author Adil
 */
public interface UserService {
	
	
	User findById(Long userId);
	
	/**
	 * Lists all tasks assigned by user, returns <code>null</code> 
	 * if the user doesn't have tasks. 
	 * 
	 * @param userId - the ID for the user
	 * 
	 * @return a List of Tasks
	 */
	List<Task> listTasksByUserId(Long userId);

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	Long createUser(String email, String password);
	
	/**
	 * 
	 * @param userId
	 * @param email
	 * @param password
	 */
	boolean updateUser(Long userId, String email, String password);
	
	/**
	 * 
	 * @param userId
	 */
	boolean deleteUser(Long userId);
	
	/**
	 * 
	 * @param password
	 * @return the Id for user
	 */
	Long passwordVerification(String email, String password);

}
