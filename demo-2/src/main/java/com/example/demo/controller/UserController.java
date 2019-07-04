package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
    UserServiceImpl userService; 
	
	  
	  @PostMapping(value = "/createUser") 
	  @ResponseStatus(value = HttpStatus.CREATED)
	  public User create(@RequestBody User user) {
	  return userRepository.save(user);
	  }
	
	 
	  @PutMapping("/update")
	  @ResponseStatus(value = HttpStatus.CREATED)
	  public User updateUser(@RequestBody User user)
	  {
		return userService.updateUser(user);
		
		  
	  }

	
	  @GetMapping("/getById/{id}")
	  @ResponseStatus(value = HttpStatus.ACCEPTED)
	  public User getUserById(@PathVariable("id") Long id) {
		  return userRepository.findOne(id);
	  }
	  
	  //userRepository.findAll(); }
	 
	
	
	  @GetMapping("/users") 
	  public List<User> getAllUsers() {
		  return userRepository.findAll(); 
		  }
	 
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") Long id)
	{
		userRepository.deleteById(id);
	}
	
	  @GetMapping("/{name}")
	  
	  @ResponseStatus(value = HttpStatus.ACCEPTED) 
	  public User getUser(@PathVariable("name") String name) 
	  { 
		  return userRepository.getUserByName(name); }
	  
	 

}
