package com.edea.tasklist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edea.tasklist.services.UserService;

@RestController
@RequestMapping("tasklist/tasks")
public class TaskController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<Object> getTaskList(@PathVariable("userId") long userId) {
		
		return ResponseEntity.ok(service.listTasksByUserId(new Long(userId)));
		
	}

}
