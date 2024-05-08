package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.controller.dto.BookDto;
import org.mate.bookstorespringboot.controller.dto.BookRequestDto;

public interface BookService {

    /**
     * Save a book.
     *
     * @param  bookRequestDto  the book to be saved
     * @return       the saved book
     */
    BookDto save(BookRequestDto bookRequestDto);

    /**
     * Find all books.
     *
     * @return         list of all books
     */
    List<BookDto> findAll();

    /**
     * Find book by id.
     *
     * @param  id  id of the book
     * @return     the book with the given id
     */
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

