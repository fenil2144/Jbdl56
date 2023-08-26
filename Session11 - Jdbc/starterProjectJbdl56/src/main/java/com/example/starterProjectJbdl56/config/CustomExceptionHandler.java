package com.example.starterProjectJbdl56.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.starterProjectJbdl56.dtos.ErrorResponse;

@Configuration
@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		BindingResult result  = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		List<String> errors = fieldErrors.stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		
		
		ErrorResponse errorResponse = ErrorResponse.builder().message(errors).status(HttpStatus.NOT_ACCEPTABLE)
			.devMessage(e.getMessage()).errorCode("ERR_406").build();
		
		return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
		
	}

}
