package com.vikas.projects.springorganicecommerceldap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.projects.springorganicecommerceldap.models.User;

@RestController
@RequestMapping(value="/user")
public class MainControllerEndPoint {
	
	
	@Autowired
	User user;
	
	@RequestMapping(value= "/create", method=RequestMethod.POST )
	public ResponseEntity<User> createUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response = new ResponseEntity<User>(user, status);
		return response;
	}
	
	@RequestMapping(value="/verify", method=RequestMethod.POST)
	public ResponseEntity<User> verifyUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	
	@RequestMapping(value="/lock", method=RequestMethod.POST)
	public ResponseEntity<User> lockUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	
	@RequestMapping(value="/deactivate", method=RequestMethod.POST)
	public ResponseEntity<User> deactivateUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	
	@RequestMapping(value="/activate", method=RequestMethod.POST)
	public ResponseEntity<User> activateUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	
	@RequestMapping(value="/unlock", method=RequestMethod.POST)
	public ResponseEntity<User> unLockUser() {
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<User> response  = new ResponseEntity<User>(user, status); 
		return response;
	}
	

}
