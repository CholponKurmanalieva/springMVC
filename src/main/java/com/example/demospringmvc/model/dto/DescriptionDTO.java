package com.example.demospringmvc.model.dto;

import lombok.*;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * @author Cholpon Kurmanalieva
 */

@Getter
@Setter
public class DescriptionDTO {
    @NonNull
    private LocalDateTime createdDate;

    public DescriptionDTO(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}