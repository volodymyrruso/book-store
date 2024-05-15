package org.mate.bookstorespringboot.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.BookDto;
import org.mate.bookstorespringboot.controller.dto.BookRequestDto;
import org.mate.bookstorespringboot.controller.mapper.BookMapper;
import org.mate.bookstorespringboot.exceptions.EntityNotFoundException;
import org.mate.bookstorespringboot.model.Book;
import org.mate.bookstorespringboot.repository.BookRepository;
import org.mate.bookstorespringboot.service.interfaces.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_FOUND = "Book not found with id: ";

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + id));
    }

    @Override
    public BookDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + id));

        Book updatedBook = bookMapper.toEntityWithId(bookRequestDto, existingBook.getId());
        return bookMapper.toDto(bookRepository.save(updatedBook));
    }

    @Override
    public void deleteById(Long id) {
        checkIfBookExists(id);
        bookRepository.deleteById(id);
    }

    private void checkIfBookExists(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(BOOK_NOT_FOUND + id);
        }
    }
}
