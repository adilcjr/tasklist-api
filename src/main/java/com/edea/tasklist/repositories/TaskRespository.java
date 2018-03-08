package com.edea.tasklist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edea.tasklist.entities.Task;

public interface TaskRespository extends CrudRepository<Task, Long> {
	
}
