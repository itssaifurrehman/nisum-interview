package com.saif.nisum.controller;

import com.saif.nisum.exception.UserManagementServiceErrorCodes;
import com.saif.nisum.exception.UserManagementServiceException;
import com.saif.nisum.model.UserDTO;
import com.saif.nisum.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
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
		UserDTO response = new UserDTO();
		try {
			response = userService.getId(id);
		} catch (UserManagementServiceException e) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.USERID_NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<UserDTO> get() throws UserManagementServiceException {

		UserDTO response = new UserDTO();
		try {
			response = (UserDTO) userService.get();
		} catch (UserManagementServiceException e) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.EMPTY_DATABASE);
		}

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

}
