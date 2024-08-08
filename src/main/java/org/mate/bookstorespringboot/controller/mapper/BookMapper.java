package org.mate.bookstorespringboot.controller.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.book.BookDtoWithoutCategoryIds;
import org.mate.bookstorespringboot.controller.dto.book.BookRequestDto;
import org.mate.bookstorespringboot.model.Book;
import org.mate.bookstorespringboot.model.Category;

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
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoriesId(categoryIds);
    }
}
