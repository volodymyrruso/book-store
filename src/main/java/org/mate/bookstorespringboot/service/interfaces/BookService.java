package org.mate.bookstorespringboot.service.interfaces;

import java.util.List;
import org.mate.bookstorespringboot.model.Book;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();
}
