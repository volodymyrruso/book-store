package org.mate.bookstorespringboot.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.book.BookDtoWithoutCategoryIds;
import org.mate.bookstorespringboot.controller.dto.category.CategoryDto;
import org.mate.bookstorespringboot.controller.dto.category.CategoryRequestDto;
import org.mate.bookstorespringboot.service.interfaces.BookService;
import org.mate.bookstorespringboot.service.interfaces.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody
                                                          CategoryRequestDto categoryRequestDto) {
        CategoryDto savedBook = categoryService.save(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CategoryDto> findAllCategories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody @Valid
                                                      CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("{id}/books")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<BookDtoWithoutCategoryIds>> getBookByCategoryId(
            @PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(bookService.findAllByCategoryId(id, pageable));
    }
}
