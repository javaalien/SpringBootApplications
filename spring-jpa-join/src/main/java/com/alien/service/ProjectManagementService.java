package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.bo.ProjectEngineerResponseBO;
import com.alien.entity.Engineer;
import com.alien.entity.Project;
import com.alien.repository.EngineerRepository;
import com.alien.repository.ProjectRepository;

@Service
public class ProjectManagementService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EngineerRepository engineerRepository;

	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	public List<Engineer> getEngineers() {
		return engineerRepository.findAll();
	}

	public String deleteProject(int projectId) {
		projectRepository.deleteById(projectId);
		return "project " + projectId + " deleted !!!";
	}

	public List<ProjectEngineerResponseBO> getProjectSpecificInfoSQL() {
		return projectRepository.getProjectSpecificInfoWithSQL();
	}

	public List<ProjectEngineerResponseBO> getProjectSpecificInfoJPQL() {
		return projectRepository.getProjectSpecificInfoWithJPQL();
	}

}
