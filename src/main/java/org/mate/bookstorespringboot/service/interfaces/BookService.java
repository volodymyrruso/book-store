package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.controller.dto.BookDto;
import org.mate.bookstorespringboot.controller.dto.BookRequestDto;

public interface BookService {

    BookDto save(BookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    /**
     * @param id id of the book
     * @return returns updated book
     */
    BookDto updateBook(Long id, BookRequestDto bookRequestDto);

    /**
     * @param id id of the book to be deleted
     */
    void deleteById(Long id);
}
