package com.alien.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.dto.FlightBookingAcknowledgement;
import com.alien.dto.FlightBookingRequest;
import com.alien.entity.PassengerInfo;
import com.alien.entity.PaymentInfo;
import com.alien.repository.PassengerInfoRepository;
import com.alien.repository.PaymentInfoRepository;
import com.alien.utils.PaymentUtils;

import jakarta.transaction.Transactional;

@Service
public class FlightBookingService {

	@Autowired
	private PassengerInfoRepository passengerInfoRepository;
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;

	@Transactional // (readOnly = false,isolation = Isolation.READ_COMMITTED,propagation =
					// Propagation.REQUIRED)
	public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {
		PassengerInfo passengerInfo = request.getPassengerInfo();
		passengerInfo = passengerInfoRepository.save(passengerInfo);

		PaymentInfo paymentInfo = request.getPaymentInfo();

		PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

		paymentInfo.setPassengerId(passengerInfo.getPId());
		paymentInfo.setAmount(passengerInfo.getFare());
		paymentInfoRepository.save(paymentInfo);

		return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(),
				UUID.randomUUID().toString().split("-")[0], passengerInfo);

	}
}
