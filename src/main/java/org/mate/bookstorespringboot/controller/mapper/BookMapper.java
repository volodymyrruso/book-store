package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.book.BookDtoWithoutCategoryIds;
import org.mate.bookstorespringboot.controller.dto.book.BookRequestDto;
import org.mate.bookstorespringboot.model.Book;

@Mapper(componentModel = "spring",config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(BookRequestDto bookRequestDto);

    default Book toEntityWithId(BookRequestDto bookRequestDto, Long id) {
        Book book = toEntity(bookRequestDto);
        book.setId(id);
        return book;
    }

    BookDtoWithoutCategoryIds toDtoWithoutCategoryIds(Book book);

    @AfterMapping
    default void setCategoryId(Book book, BookDto bookDto) {

    }
}
