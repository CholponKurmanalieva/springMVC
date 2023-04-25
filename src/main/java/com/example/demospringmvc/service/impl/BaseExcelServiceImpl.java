package com.example.demospringmvc.service.impl;

import com.example.demospringmvc.service.BaseExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author Cholpon Kurmanalieva
 */

@Service
@Slf4j
public class BaseExcelServiceImpl<T> implements BaseExcelService<T> {
    @Override
    public Workbook getCreatedWorkBook(String sheetName, List<T> list, Field[] fields) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        int row = 1;

        createTitleOfExcel(fields, sheet, workbook);

        for (T t : list) {
           try {
               createCell(row, sheet, fields, t);
               row++;
           } catch (IllegalAccessException ex) {
               log.error("Error occurred: {}", ex.getMessage());
           }
        }

        return workbook;
    }

    private void createTitleOfExcel(Field[] fields, Sheet sheet, Workbook workbook) {
        Row row = sheet.createRow(0);

        Font font = workbook.createFont();
        font.setBold(true);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);

        int cell = 0;

        for (Field field : fields) {
            field.setAccessible(true);

            Cell newCell = row.createCell(cell);
            newCell.setCellValue(field.getName());
            newCell.setCellStyle(cellStyle);

            cell++;
        }
    }

    private void createCell(int row, Sheet sheet, Field[] fields, T t) throws IllegalAccessException {
        int cell = 0;

        Row newRow = sheet.createRow(row);

        for (Field field : fields) {
            field.setAccessible(true);

            if (Objects.nonNull(field.get(t))) {
                newRow.createCell(cell).setCellValue(field.get(t).toString());
            } else {
                newRow.createCell(cell).setCellValue("null");
            }
            cell++;
        }
    }
}