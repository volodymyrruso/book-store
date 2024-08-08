package org.mate.bookstorespringboot.service.interfaces;

import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.book.BookDtoWithoutCategoryIds;
import org.mate.bookstorespringboot.controller.dto.book.BookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(BookRequestDto bookRequestDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto updateBook(Long id, BookRequestDto bookRequestDto);

    void deleteById(Long id);

    Page<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id, Pageable pageable);
}
