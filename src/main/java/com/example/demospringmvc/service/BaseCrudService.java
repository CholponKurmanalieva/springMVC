package com.example.demospringmvc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BaseCrudService<T> {
    Page<T> getAll(Pageable pageable);
    T save(T t);
}