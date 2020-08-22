package com.saif.nisum.controller;

import com.saif.nisum.exception.UserManagementServiceException;
import com.saif.nisum.model.UserDTO;
import java.util.List;

import com.saif.nisum.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user/controller")
public class UserController {

	@Autowired
	private UserService userService;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) throws Exception {

		UserDTO response = userService.create(userDTO);

		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {

		UserDTO response = userService.update(userDTO);

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") String id) throws UserManagementServiceException {

		UserDTO response = userService.getId(id);

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<UserDTO>> get() throws UserManagementServiceException {

		List<UserDTO> response = userService.get();

		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("userId") String id) throws UserManagementServiceException {
		userService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
