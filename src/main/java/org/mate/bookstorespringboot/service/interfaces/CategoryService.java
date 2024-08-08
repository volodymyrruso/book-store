package org.mate.bookstorespringboot.service.interfaces;

import org.mate.bookstorespringboot.controller.dto.category.CategoryDto;
import org.mate.bookstorespringboot.controller.dto.category.CategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CategoryRequestDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryRequestDto categoryDto);

    void deleteById(Long id);
}
