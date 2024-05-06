package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.model.Book;

public interface BookService {

    /**
     * Save a book.
     *
     * @param  book  the book to be saved
     * @return       the saved book
     */
    Book save(Book book);

    /**
     * Find all books.
     *
     * @return         list of all books
     */
    List<Book> findAll();
}
