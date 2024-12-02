package com.alien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.entity.Course;
import com.alien.repository.CourseRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

	private CourseRepository courseRepository;

	public Course addNewCourse(Course course) {
		log.info("CourseService::addNewCourse method executed");
		return courseRepository.save(course);
	}

	public List<Course> getAllAvailableCourses() {
		log.info("CourseService::getAllAvailableCourses method executed");
		return courseRepository.findAll();
	}

}
