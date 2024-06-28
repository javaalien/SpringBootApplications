package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
