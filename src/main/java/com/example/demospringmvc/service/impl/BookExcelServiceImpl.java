package com.example.demospringmvc.service.impl;

import com.example.demospringmvc.exception.OutputStreamExcelException;
import com.example.demospringmvc.model.dto.BookExcelDTO;
import com.example.demospringmvc.repository.BookRepository;
import com.example.demospringmvc.service.BaseExcelService;
import com.example.demospringmvc.service.BookExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Cholpon Kurmanalieva
 */

@Service
@Slf4j
public class BookExcelServiceImpl implements BookExcelService {
    private final BookRepository bookRepository;
    private final BaseExcelService<BookExcelDTO> baseExcelService;

    public BookExcelServiceImpl(BookRepository bookRepository, BaseExcelService<BookExcelDTO> baseExcelService) {
        this.bookRepository = bookRepository;
        this.baseExcelService = baseExcelService;
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws OutputStreamExcelException {
        List<BookExcelDTO> bookExcelDTOS = bookRepository.getAllBookExcelDTO();
        Field[] fields = BookExcelDTO.class.getDeclaredFields();

        Workbook workbook = baseExcelService.getCreatedWorkBook("List of books", bookExcelDTOS, fields);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException ex) {
            throw new OutputStreamExcelException(ex.getMessage());
        }
    }
}