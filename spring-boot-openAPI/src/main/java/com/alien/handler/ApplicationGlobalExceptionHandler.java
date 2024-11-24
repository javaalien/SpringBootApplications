package com.alien.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alien.dto.ErrorDTO;
import com.alien.dto.ServiceResponse;
import com.alien.exception.CourseServiceBusinessException;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
		ServiceResponse<?> serviceResponse = new ServiceResponse<>();

		List<ErrorDTO> errorDTOList = new ArrayList<>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			ErrorDTO errorDTO = new ErrorDTO(error.getField() + " : " + error.getDefaultMessage());
			errorDTOList.add(errorDTO);
		});

		serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
		return serviceResponse;
	}

	@ExceptionHandler(CourseServiceBusinessException.class)
	public ServiceResponse<?> handleServiceException(CourseServiceBusinessException exception) {
		ServiceResponse<?> serviceResponse = new ServiceResponse<>();
		List<ErrorDTO> errorDTOList = new ArrayList<>();
		errorDTOList.add(new ErrorDTO(exception.getMessage()));

		serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		serviceResponse.setError(errorDTOList);
		return serviceResponse;
	}
}