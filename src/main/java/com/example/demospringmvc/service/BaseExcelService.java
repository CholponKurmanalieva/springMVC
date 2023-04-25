package com.example.demospringmvc.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BaseExcelService<T> {
    Workbook getCreatedWorkBook(String sheetName, List<T> list, Field[] fields);
}