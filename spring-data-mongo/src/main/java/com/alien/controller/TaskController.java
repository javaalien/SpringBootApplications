package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.entity.Task;
import com.alien.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public Task addNewTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}

	@GetMapping
	public List<Task> findAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/{taskId}")
	public Task findTaskByTaskId(@PathVariable String taskId) {
		return taskService.getTask(taskId);
	}

	@PutMapping
	public Task updateTask(@RequestBody Task taskRequest) {
		return taskService.updateTask(taskRequest);
	}

	@DeleteMapping("/{taskId}")
	public String deleteTask(@PathVariable String taskId) {
		return taskService.deleteTask(taskId);
	}

	@GetMapping("/assignee/{assignee}/priority/{priority}")
	public List<Task> getTaskByAssigneeAndPriority(@PathVariable String assignee, @PathVariable String priority) {
		return taskService.getTaskByAssigneeAndPriority(assignee, priority);
	}
}
