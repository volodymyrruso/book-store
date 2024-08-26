package org.mate.bookstorespringboot.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.category.CategoryDto;
import org.mate.bookstorespringboot.controller.dto.category.CategoryRequestDto;
import org.mate.bookstorespringboot.controller.mapper.CategoryMapper;
import org.mate.bookstorespringboot.exceptions.EntityNotFoundException;
import org.mate.bookstorespringboot.model.Category;
import org.mate.bookstorespringboot.repository.CategoryRepository;
import org.mate.bookstorespringboot.service.interfaces.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found with id: ";
    private final CategoryRepository categoryRepository;

    private final CategoryMapper mapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        List<CategoryDto> list = categoryRepository.findAll(pageable).stream()
                .map(mapper::toDto)
                .toList();
        Page<CategoryDto> pageDto = new PageImpl<>(list);
        return pageDto;
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND + id));
    }

    @Override
    public CategoryDto save(CategoryRequestDto categoryDto) {
        Category savedCategory = mapper.toEntity(categoryDto);
        return mapper.toDto(categoryRepository.save(savedCategory));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryRequestDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND + id));
        if (categoryDto.name() != null) {
            existingCategory.setName(categoryDto.name());
        }
        if (categoryDto.description() != null) {
            existingCategory.setDescription(categoryDto.description());
        }
        Category updatedCategory = categoryRepository.save(existingCategory);
        return mapper.toDto(updatedCategory);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND + id);
        }
        categoryRepository.deleteById(id);
    }
}
