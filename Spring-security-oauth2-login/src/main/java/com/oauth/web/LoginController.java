package com.oauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@GetMapping(value = { "/", "/login" })
	public String login() {
		return "oauth2Login";
	}

	@GetMapping("/error")
	public String error(HttpServletRequest request) {

		String message = (String) request.getSession().getAttribute("error.message");
		request.getSession().removeAttribute("error.message");
		return message;

	}

}
