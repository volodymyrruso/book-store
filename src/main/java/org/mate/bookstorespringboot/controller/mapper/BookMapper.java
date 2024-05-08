package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.BookDto;
import org.mate.bookstorespringboot.controller.dto.BookRequestDto;
import org.mate.bookstorespringboot.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookRequestDto bookRequestDto);
}
