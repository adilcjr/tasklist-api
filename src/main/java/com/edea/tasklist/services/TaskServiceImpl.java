package com.edea.tasklist.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edea.tasklist.entities.Task;
import com.edea.tasklist.repositories.TaskRespository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRespository taskRepository;

	public Long createTask(String title, String description) {
		
		if (title == null) return null;

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setCreationDate(new Date());
		taskRepository.save(task);
		
		return task.getId();
	}

	public boolean updateTask(Long taskId, String title, String description, boolean done) {
			
		Task task = taskRepository.findOne(taskId);
		
		if (task != null) {
			
			if (title != null) task.setTitle(title);
			if (description != null) task.setDescription(description);
			if (done) {
				task.setDoneDate(new Date());
			} else {
				task.setDoneDate(null);
			}
			
			task.setUpdateDate(new Date());
			
			taskRepository.save(task);
			return true;
		}
		
		return false;
	}

	public boolean deleteTask(Long taskId) {
		
		Task task = taskRepository.findOne(taskId);
		
		if (task != null && task.getDeleteDate() == null) { 
									
			task.setDeleteDate(new Date());
			taskRepository.save(task);
			return true;
		}
		return false;
	}
	
}
