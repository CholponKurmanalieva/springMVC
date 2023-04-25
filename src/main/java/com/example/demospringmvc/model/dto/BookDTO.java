package com.example.demospringmvc.model.dto;

import com.example.demospringmvc.annotation.AccessibleGenre;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Cholpon Kurmanalieva
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    public UUID id;

    @NotBlank(message = "Author may not be blank")
    @Length(min = 2, max = 100, message = "Minimum name length is 2 and maximum name length is 100")
    private String author;

    @NotBlank(message = "Title may not be blank")
    private String title;

    @AccessibleGenre
    private String genre;

    @Positive
    private Long volume;

    @Positive
    private Double price;

    private DescriptionDTO descriptionDTO;

    public BookDTO(UUID id, String title, String author, String genre, Long volume, Double price, LocalDateTime createdDate) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.volume = volume;
        this.descriptionDTO = new DescriptionDTO(createdDate);
        this.id = id;
    }

    public BookDTO(UUID id, String title, String author, String genre, Long volume, Double price) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.volume = volume;
        this.id = id;
    }
}