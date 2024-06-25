package com.dtodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtodemo.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
