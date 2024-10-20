package com.alien.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collation = "Tasks")
@Data	
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	@Id
	private String taskId;
	private String description;
	private String priority; // p1 ,p2 , p3
	private String assignee;
	private int storyPoint;	// 5

}
