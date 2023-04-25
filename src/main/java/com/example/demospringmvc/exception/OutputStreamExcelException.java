package com.example.demospringmvc.exception;

import java.io.IOException;

/**
 * @author Cholpon Kurmanalieva
 */

public class OutputStreamExcelException extends IOException {
    public OutputStreamExcelException(String message) {
        super(message);
    }
}