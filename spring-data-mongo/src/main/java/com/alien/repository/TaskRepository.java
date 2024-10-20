package com.alien.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.alien.entity.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findByAssigneeAndPriority(String assignee, String priority);

	@Query(value = "{assignee: ?0 ,priority: ?1}", fields = "{'description' : 1 , 'storyPoint': 2}")
	List<Task> finTaskWithAssigneeAndPriority(String assignee, String priority);
	
	

}
