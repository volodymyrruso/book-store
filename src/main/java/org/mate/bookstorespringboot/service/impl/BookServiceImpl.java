package org.mate.bookstorespringboot.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.book.BookDtoWithoutCategoryIds;
import org.mate.bookstorespringboot.controller.dto.book.BookRequestDto;
import org.mate.bookstorespringboot.controller.mapper.BookMapper;
import org.mate.bookstorespringboot.exceptions.EntityNotFoundException;
import org.mate.bookstorespringboot.model.Book;
import org.mate.bookstorespringboot.repository.BookRepository;
import org.mate.bookstorespringboot.repository.CategoryRepository;
import org.mate.bookstorespringboot.service.interfaces.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_FOUND = "Book not found with id: ";
    private static final String CATEGORY_NOT_FOUND = "Category not found with id: ";

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(BookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookRequestDto)));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        List<BookDto> list = bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
        Page<BookDto> page = new PageImpl<>(list);
        return page;
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

    @Override
    public Page<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id, Pageable pageable) {
        List<BookDtoWithoutCategoryIds> list = bookRepository
                .findAllByCategoriesId(id, pageable).stream()
                .map(bookMapper::toDtoWithoutCategoryIds)
                .toList();
        Page page = new PageImpl(list);
        return page;
    }

    private void checkIfBookExists(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(BOOK_NOT_FOUND + id);
        }
    }
}
