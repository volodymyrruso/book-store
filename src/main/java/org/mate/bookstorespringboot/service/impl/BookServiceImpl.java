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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookRequestDto bookRequestDto) {
        Book book = bookMapper.toEntity(bookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }
}
