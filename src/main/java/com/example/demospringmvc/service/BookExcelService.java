package com.example.demospringmvc.service;

import com.example.demospringmvc.exception.OutputStreamExcelException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BookExcelService {
    void exportToExcel(HttpServletResponse response) throws OutputStreamExcelException;
}