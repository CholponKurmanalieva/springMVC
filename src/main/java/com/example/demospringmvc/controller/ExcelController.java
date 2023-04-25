package com.example.demospringmvc.controller;

import com.example.demospringmvc.exception.OutputStreamExcelException;
import com.example.demospringmvc.service.impl.BookExcelServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author Cholpon Kurmanalieva
 */

@RestController
@RequestMapping("/excel")
public class ExcelController {
    private final BookExcelServiceImpl bookExcelService;

    public ExcelController(BookExcelServiceImpl excelService) {
        this.bookExcelService = excelService;
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadExcel(HttpServletResponse response) throws OutputStreamExcelException {
        response.setHeader("Content-Disposition", "attachment; filename=Books" + LocalDateTime.now() + ".xls");
        response.setContentType("application/octet-stream");

        bookExcelService.exportToExcel(response);

        return ResponseEntity.ok().build();
    }
}