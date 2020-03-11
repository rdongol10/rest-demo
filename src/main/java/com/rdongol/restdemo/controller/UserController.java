package com.rdongol.restdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdongol.restdemo.entity.User;
import com.rdongol.restdemo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		if (!user.isPresent()) {
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(user.get());
	}

	@PostMapping
	public ResponseEntity<User> create(User user) {
		System.out.println(user.toString());
		return ResponseEntity.ok(userService.save(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, User user) {
		if (!userService.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		User currentUser = userService.findById(id).get();

		currentUser.setFirstName(user.getFirstName());
		currentUser.setMiddleName(user.getMiddleName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPhoneNumber(user.getPhoneNumber());
		currentUser.setEmailAddress(user.getEmailAddress());
		currentUser.setSex(user.getSex());
		currentUser.setTypeOfUser(user.getTypeOfUser());

		return ResponseEntity.ok(userService.save(currentUser));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		if (!userService.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}

		userService.deleteById(id);

		return ResponseEntity.ok().build();
	}

}
