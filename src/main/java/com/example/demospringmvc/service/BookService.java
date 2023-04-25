package com.example.demospringmvc.service;

import com.example.demospringmvc.model.dto.BookDTO;

import java.util.UUID;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BookService extends BaseCrudService<BookDTO> {
    BookDTO getById(UUID id);
    void update(UUID id, BookDTO bookDTO);
    void delete(UUID id);
}