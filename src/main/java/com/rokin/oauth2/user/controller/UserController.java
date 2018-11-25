package com.rokin.oauth2.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokin.oauth2.user.model.User;
import com.rokin.oauth2.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		return this.userService.saveUser(user);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping
	public List<User> getAllUsers() {
		return this.userService.getAll();
	}

}
