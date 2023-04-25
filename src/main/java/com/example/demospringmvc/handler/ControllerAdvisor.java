package com.example.demospringmvc.handler;

import com.example.demospringmvc.exception.OutputStreamExcelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Cholpon Kurmanalieva
 */

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(OutputStreamExcelException.class)
    public ResponseEntity<?> handleOutputStreamExcelException() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("date", LocalDateTime.now());
        body.put("message", "Occurred error when outputting data to excel");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}