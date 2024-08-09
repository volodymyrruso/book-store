package org.mate.bookstorespringboot.controller.dto.cartitem;

public record CartItemDto(Long id,
                          Long bookId,
                          String bookName,
                          int quantity) {
}
