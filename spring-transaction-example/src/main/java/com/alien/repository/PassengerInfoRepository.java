package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.PassengerInfo;

public interface PassengerInfoRepository extends JpaRepository<PassengerInfo,Long> {

}
