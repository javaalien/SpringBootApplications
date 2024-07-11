package com.mvc.config.security;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ex) throws IOException, ServletException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		String jsonPayload = "{\"message\" : \"%s\", \"timestamp\" : \"%s\" }";

		response.getOutputStream()
				.println(String.format(jsonPayload, ex.getMessage(), Calendar.getInstance().getTime()));

	}

}
