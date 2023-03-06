package com.Student.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@Component
@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(LimitCross.class)
	public ResponseEntity<String> limitCross(){
		return new ResponseEntity<String>("Exceeded the group limit",HttpStatus.INSUFFICIENT_STORAGE);
	}
	
	@ExceptionHandler(Empty.class)
	public ResponseEntity<String> empty(){
		return new ResponseEntity<String>("You have not joined any classes",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(StudentNotFound.class)
	public ResponseEntity<String> studentNotFound(){
		return new ResponseEntity<String>("This student has not registered any class",HttpStatus.NOT_FOUND);
	}
	

}
