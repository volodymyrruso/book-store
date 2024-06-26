package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.book.BookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(BookRequestDto bookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto updateBook(Long id, BookRequestDto bookRequestDto);

    void deleteById(Long id);
}
