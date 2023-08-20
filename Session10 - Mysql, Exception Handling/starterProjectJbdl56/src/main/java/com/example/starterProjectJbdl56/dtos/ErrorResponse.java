package com.example.starterProjectJbdl56.dtos;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
	
	private List<String> message;
	private HttpStatus status;
	private String devMessage;
	private String errorCode;

}
