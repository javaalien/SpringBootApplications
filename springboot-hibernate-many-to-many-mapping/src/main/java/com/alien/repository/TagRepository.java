package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alien.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
