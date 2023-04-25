package com.example.demospringmvc.mapper;

import com.example.demospringmvc.model.dto.BookDTO;
import com.example.demospringmvc.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Cholpon Kurmanalieva
 */

@Mapper(config = MapstructAutoWire.class, uses = {DescriptionMapper.class})
public interface BookMapper extends BaseMapper<Book, BookDTO> {
    @Override
    @Mapping(target = "description", source = "descriptionDTO")
    Book toEntity(BookDTO bookDTO);
}