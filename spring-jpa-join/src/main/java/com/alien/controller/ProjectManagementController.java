package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.bo.ProjectEngineerResponseBO;
import com.alien.entity.Engineer;
import com.alien.entity.Project;
import com.alien.service.ProjectManagementService;

@RestController
@RequestMapping("/jpa")
public class ProjectManagementController {

	@Autowired
	private ProjectManagementService service;

	@PostMapping("/projects")
	public Project addNewProject(@RequestBody Project project) {
		return service.saveProject(project);
	}

	@GetMapping("/projects")
	public List<Project> getProjects() {
		return service.getProjects();
	}

	@GetMapping("/engineers")
	public List<Engineer> getEngineers() {
		return service.getEngineers();
	}

	@DeleteMapping("/project/{projectId}")
	public String deleteProject(@PathVariable int projectId) {
		return service.deleteProject(projectId);
	}

	@GetMapping("/join/sql")
	public List<ProjectEngineerResponseBO> getProjectSpecificInfoSQL() {
		return service.getProjectSpecificInfoSQL();
	}

	@GetMapping("/join/jpql")
	public List<ProjectEngineerResponseBO> getProjectSpecificInfoJPQL() {
		return service.getProjectSpecificInfoJPQL();
	}
}
