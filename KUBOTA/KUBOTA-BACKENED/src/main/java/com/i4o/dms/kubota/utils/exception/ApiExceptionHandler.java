//package com.i4o.dms.kubota.utils.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Date;
//@ControllerAdvice
//public class ApiExceptionHandler
//{
//    @ExceptionHandler(value = {ApiRequestException.class})
//    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e, WebRequest request, HttpStatus httpStatus)
//    {
//        ApiException apiException=new ApiException(new Date(),e.getMessage(),request.getDescription(false));
//        return  new ResponseEntity<>(apiException,httpStatus);
//    }
//
//}
