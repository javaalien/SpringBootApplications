package com.alien.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.entity.Course;
import com.alien.service.CourseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
@Slf4j
public class CourseController {

	private CourseService courseService;

	@PostMapping
	public Course addCourse(@RequestBody Course course) {
		log.info("CourseController::addCourse method executed");
		return courseService.addNewCourse(course);
	}

	@GetMapping
	public List<Course> viewAllCourses() {
		log.info("CourseController::viewAllCourses method executed");
		return courseService.getAllAvailableCourses();
	}
}
