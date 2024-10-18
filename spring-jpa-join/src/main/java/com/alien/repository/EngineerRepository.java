package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Engineer;

public interface EngineerRepository extends JpaRepository<Engineer, Integer> {
}
