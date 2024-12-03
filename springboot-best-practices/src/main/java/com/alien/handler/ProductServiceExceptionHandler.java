package com.alien.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alien.dto.APIResponse;
import com.alien.dto.ErrorDTO;
import com.alien.exception.ProductNotFoundException;
import com.alien.exception.ProductServiceBusinessException;

@RestControllerAdvice
public class ProductServiceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {

		APIResponse<?> serviceResponse = new APIResponse<>();

		List<ErrorDTO> errors = new ArrayList<>();

		exception.getBindingResult().getFieldErrors().forEach(error -> {
			ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
			errors.add(errorDTO);
		});

		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(errors);
		return serviceResponse;
	}

	@ExceptionHandler(ProductServiceBusinessException.class)
	public APIResponse<?> handleServiceException(ProductServiceBusinessException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public APIResponse<?> handleProductNotFoundException(ProductNotFoundException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}
}
