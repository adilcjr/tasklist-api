package com.edea.tasklist.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edea.tasklist.dto.TaskDTO;
import com.edea.tasklist.dto.UserDTO;
import com.edea.tasklist.entities.Task;
import com.edea.tasklist.responses.Response;
import com.edea.tasklist.services.UserService;

@RestController
@RequestMapping("tasklist/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/{userId}/tasks")
	public ResponseEntity<Object> getTaskList(@PathVariable("userId") long userId) {

		List<Task> tasks = userService.listTasksByUserId(new Long(userId));

		if (tasks != null) {

			List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>(tasks.size());

			tasks.forEach(task -> {
				TaskDTO taskDTO = new TaskDTO();
				taskDTO.setId(task.getId());
				taskDTO.setTitle(task.getTitle());
				taskDTO.setDescription(task.getDescription());
				taskDTO.setStatus(task.isStatus());
				tasksDTO.add(taskDTO);
			});

			return ResponseEntity.ok(tasksDTO);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user, BindingResult result) {

		Response<UserDTO> response = new Response<UserDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		userService.createUser(user.getEmail(), user.getPassword());

		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable("userId") long userId, 
			@Valid @RequestBody UserDTO user,
			BindingResult result) {

		Response<UserDTO> response = new Response<UserDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		if (userService.updateUser(userId, user.getEmail(), user.getPassword()))
			return ResponseEntity.ok(response);

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping(value="/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") long userId) {
		
		
		if (userService.deleteUser(userId))
			return ResponseEntity.ok().build();
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/auth")
	public ResponseEntity<Object> passwordVerification(@RequestBody UserDTO user) {
		
		if (userService.passwordVerification(user.getEmail(), user.getPassword()))
			return ResponseEntity.ok().build();
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
