package com.alien.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.AuthRequest;
import com.alien.entity.Employee;
import com.alien.service.EmployeeService;
import com.alien.service.JwtService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	public static final String AUTHORITY_ROLE_HR = "hasAuthority('ROLE_HR')";

	@Autowired
	private EmployeeService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the JavaAlien ! . Your official credential already has been shared over email";
	}

	@PostMapping("/authenticate")
	public String authenticate(@RequestBody AuthRequest authRequest) {

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("Authentication failed !");
		}
	}

	@PostMapping("/create")
	public Employee onboardNewEmployee(@RequestBody Employee employee) {
		return service.createNewEmployee(employee);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_HR') or hasAuthority('ROLE_MANAGER')")
	public List<Employee> getAll() {
		return service.getEmployees();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public Employee getEmployeeById(@PathVariable Integer id) {
		return service.getEmployee(id);
	}

	@PutMapping("/update")
	@PreAuthorize(AUTHORITY_ROLE_HR)
	public Employee updateRoles(@RequestBody Employee employee) {
		return service.changeRoleOfEmployee(employee);
	}

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	public String test(Principal principal) {
		return principal.getName();
	}

}
