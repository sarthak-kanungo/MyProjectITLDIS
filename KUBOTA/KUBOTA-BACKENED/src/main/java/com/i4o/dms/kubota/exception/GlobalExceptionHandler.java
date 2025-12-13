package com.i4o.dms.kubota.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.exception.ApiException;
import com.i4o.dms.kubota.utils.exception.ApiRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // Handling generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        
        logger.error("An error occurred: ", ex);
        
        apiResponse.setMessage("An unexpected error occurred: " + ex.getMessage());
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    // Handling specific HttpRequestMethodNotSupportedException
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        
        apiResponse.setMessage("HTTP method not allowed: " + ex.getMethod());
        apiResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiResponse);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	ApiResponse<?> apiResponse = new ApiResponse<>();
    	
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        apiResponse.setMessage("Validation Failed" + ex.getMessage());
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
    
    @ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e, WebRequest request, HttpStatus httpStatus)
	{
		ApiException apiException=new ApiException(new Date(), e.getMessage(), request.getDescription(false));
		return  new ResponseEntity<>(apiException,httpStatus);
	}
    
    // Add other exception handlers as needed (e.g., for custom exceptions)
}
