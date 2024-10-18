package com.alien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alien.bo.ProjectEngineerResponseBO;
import com.alien.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query(value = "select new com.alien.bo.ProjectEngineerResponseBO(p.name as  project_name, p.project_code, e.name as engineer_name, e.email) from Project p join Engineer e on p.project_id = e.project_engineer_fk", nativeQuery = true)
	public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithSQL();

	@Query(value = "SELECT new com.alien.bo.ProjectEngineerResponseBO(p.name , p.projectCode , e.name , e.email) FROM Project p JOIN p.engineers e")
	public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithJPQL();

}
