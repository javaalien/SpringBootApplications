package com.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noAuth")
public class NoAuthController {

	@GetMapping("/Hi")
	public String sayHi() {
		return "hi, welcome to java alien url with no Authorization.";
	}

}
