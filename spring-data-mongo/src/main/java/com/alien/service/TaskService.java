package com.alien.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.entity.Task;
import com.alien.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;

	public Task saveTask(Task task) {
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return repository.save(task);
	}

	public List<Task> getAllTasks() {
		return repository.findAll();
	}

	public Task getTask(String taskId) {
		return repository.findById(taskId).get();
	}

	public Task updateTask(Task taskRequest) {
		Task existingTask = repository.findById(taskRequest.getTaskId()).get();

		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setPriority(taskRequest.getPriority());
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		return repository.save(existingTask);

	}

	public String deleteTask(String taskId) {
		repository.deleteById(taskId);

		return taskId + "task is deleted";

	}

	public List<Task> getTaskByAssigneeAndPriority(String assignee, String priority) {
		return repository.finTaskWithAssigneeAndPriority(assignee, priority);
	}

}
