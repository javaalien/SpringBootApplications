package com.alien.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogExecutionTracker {

	@Around("@annotation(com.alien.annotation.TrackExecutionTime)")
	public Object logExecutionDuration(ProceedingJoinPoint pjp) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		// after advice
		long endTime = System.currentTimeMillis();
		log.info(" method {} execution takes {} ms times to complete ", pjp.getSignature(), (endTime - startTime));
		return obj;

	}

}
