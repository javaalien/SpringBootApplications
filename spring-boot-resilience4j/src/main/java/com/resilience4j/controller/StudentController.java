package com.resilience4j.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class StudentController {

	@GetMapping(value = "/student/{id}")
	@RateLimiter(name = "rateLimitingAPI", fallbackMethod = "rateLimitingFallback")
	public ResponseEntity getStudentById(@PathVariable int id) {
		return ResponseEntity.ok("Details requested for :" + id);
	}

	@GetMapping("/course/{id}")
	@Bulkhead(name = "courseBulkheadApi", fallbackMethod = "bulkheadFallback")
	public ResponseEntity getCourse(@PathVariable int id) {

		System.out.println("In course details for: " + id);

		try {

			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Returning course details for: " + id);

		return ResponseEntity.ok("Course :" + id);
	}

	public ResponseEntity bulkheadFallback(int id, BulkheadFullException ex) {

		System.out.println("Bulkhead applied no further calls are accepted");

		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.body("Too many Requests - no further calls are accepted.");
	}

	public ResponseEntity rateLimitingFallback(int id, RequestNotPermitted ex) {

		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Retry-After", "60s"); // retry after one second

		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).headers(responseHeaders)
				.body("Too Many Requests - Retry After 1 Minute");

	}
}
