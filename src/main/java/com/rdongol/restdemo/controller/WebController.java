package com.rdongol.restdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/userList")
	public String listUsers() {
		return "userList";
	}
}
