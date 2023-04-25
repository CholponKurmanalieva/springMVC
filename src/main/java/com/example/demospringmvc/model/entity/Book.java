package com.example.demospringmvc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Cholpon Kurmanalieva
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Entity
@Builder
public class Book extends BaseEntity {
    @Column(name = "author", updatable = false)
    private String author;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "price")
    private Double price;

    @Embedded
    Description description;
}