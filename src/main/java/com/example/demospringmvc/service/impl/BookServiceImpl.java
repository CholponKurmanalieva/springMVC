package com.example.demospringmvc.service.impl;

import com.example.demospringmvc.mapper.BookMapper;
import com.example.demospringmvc.model.dto.BookDTO;
import com.example.demospringmvc.model.entity.Book;
import com.example.demospringmvc.model.entity.Description;
import com.example.demospringmvc.repository.BookRepository;
import com.example.demospringmvc.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Cholpon Kurmanalieva
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

//    TODO dateTimeFormatter
    @Override
    public Page<BookDTO> getAll(Pageable pageable) {
        return bookRepository.getAllBooks(pageable);
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setDescription(new Description());

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDTO getById(UUID id) {
        return bookRepository.getBookDTOById(id);
    }

    @Override
    public void update(UUID id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Description description = book.getDescription();

            book.setAuthor(bookDTO.getAuthor());
            book.setTitle(bookDTO.getTitle());
            book.setGenre(bookDTO.getGenre());
            book.setPrice(bookDTO.getPrice());
            book.setVolume(bookDTO.getVolume());
            book.setDescription(description);

            bookRepository.save(book);
            log.info("{}, is updated", book);
        }
    }

    @Override
    public void delete(UUID id) {
        Book book = bookRepository.findByDescriptionDeletedDateIsNullAndId(id);

        if (Objects.nonNull(book)) {
            Description description = book.getDescription();

            description.setDeletedDate(LocalDate.now());
            book.setDescription(description);

            bookRepository.save(book);
            log.info("{}, is deleted", book);
        }
    }
}