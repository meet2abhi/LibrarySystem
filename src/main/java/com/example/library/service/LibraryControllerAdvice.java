package com.example.library.service;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
public class LibraryControllerAdvice {

	@ExceptionHandler({ NoSuchElementException.class })
	public ResponseEntity<Void> handleNotAvailableException(Exception exception) {
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( { MethodNotAllowedException.class })
	public ResponseEntity<Void> handleBookAlreadyLoanedException(Exception exception) {
		return new ResponseEntity<>(null, HttpStatus.GONE);
	}

}
