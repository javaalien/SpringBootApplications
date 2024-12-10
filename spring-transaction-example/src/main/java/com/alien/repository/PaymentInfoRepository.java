package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String> {
}
