package com.alien.dao;

import org.springframework.data.repository.CrudRepository;

import com.alien.entity.CourseEntity;

public interface CourseDao extends CrudRepository<CourseEntity, Integer>{

}
