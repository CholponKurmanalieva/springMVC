package com.example.demospringmvc.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Cholpon Kurmanalieva
 */

@Getter
@Setter
public class BookExcelDTO {
    private String author;
    private String title;
    private String genre;
    private Long volume;
    private Double price;

    public BookExcelDTO(String author, String title, String genre, Long volume, Double price) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.volume = volume;
        this.price = price;
    }
}