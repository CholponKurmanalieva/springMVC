package com.example.demospringmvc.repository;

import com.example.demospringmvc.model.dto.BookDTO;
import com.example.demospringmvc.model.dto.BookExcelDTO;
import com.example.demospringmvc.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT " +
            "new com.example.demospringmvc.model.dto.BookDTO(b.id, b.title, b.author, b.genre, b.volume, b.price, b.description.createdDate)" +
            " FROM Book b WHERE b.description.deletedDate IS NULL")
    Page<BookDTO> getAllBooks(Pageable pageable);

    @Query("SELECT " +
            "new com.example.demospringmvc.model.dto.BookDTO(b.id, b.title, b.author, b.genre, b.volume, b.price)" +
            " FROM Book b WHERE b.id = :id")
    BookDTO getBookDTOById(@Param("id") UUID uuid);

    @Query("SELECT new com.example.demospringmvc.model.dto.BookExcelDTO(b.author, b.title, b.genre, b.volume, b.price) FROM Book b WHERE b.description.deletedDate IS NULL")
    List<BookExcelDTO> getAllBookExcelDTO();
    Book findByDescriptionDeletedDateIsNullAndId(UUID id);
}