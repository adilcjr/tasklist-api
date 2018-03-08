package com.edea.tasklist.services;

public interface TaskService {
	
	Long createTask(String title, String description);
	
	boolean updateTask(Long taskId, String title, String description, boolean status);
	
	boolean deleteTask(Long taskId);
	
}
