package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.category.CategoryDto;
import org.mate.bookstorespringboot.controller.dto.category.CategoryRequestDto;
import org.mate.bookstorespringboot.model.Category;

@Mapper(componentModel = "spring",config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryDto);
}
