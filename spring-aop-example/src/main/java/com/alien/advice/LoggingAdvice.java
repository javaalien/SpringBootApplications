package com.alien.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

	@Pointcut("execution(* com.alien.*.*.*(..))")
	private void logPointcut() {
	}

	// @Before("logPointcut()")
	public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
		log.info("class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
		log.info("Request Body {} ", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
	}

//  @AfterReturning(value = "execution (* com.alien.controller.ProductController.*(..))",returning = "object")
//  public void logResponse(JoinPoint joinPoint,Object object) throws JsonProcessingException {
//      log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(), joinPoint.getSignature().getName());
//      log.info("LoggingAdvice::logResponse Response Body {} ", new ObjectMapper().writeValueAsString(object));
//  }

	// @After(value = "execution (* com.alien.controller.ProductController.*(..))")
	public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
		log.info("LoggingAdvice::logResponse class name {} ,method name {} ", joinPoint.getTarget(),
				joinPoint.getSignature().getName());
		log.info("LoggingAdvice::logResponse Response Body {} ",
				new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
	}

}
