package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}