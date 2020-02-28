package com.bank.web.rest.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value =BusinessException.class)
    public ResponseEntity exceptionHandler(BusinessException e){
        System.out.println("未知异常！原因是:"+e);
        return ResponseEntity.status(666).body(new ErrorBody(e.getMessage()));
    }
}
