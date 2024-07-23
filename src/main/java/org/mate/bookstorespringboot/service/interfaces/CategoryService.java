package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.controller.dto.category.CategoryDto;
import org.mate.bookstorespringboot.controller.dto.category.CategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CategoryRequestDto categoryDto);

    CategoryDto updateCategory(Long id, CategoryRequestDto categoryDto);

    void deleteById(Long id);
}
