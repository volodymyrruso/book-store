package org.mate.bookstorespringboot.controller.dto.cartitem;

import jakarta.validation.constraints.Min;

public record CartItemRequestDto(@Min(1) int quantity,
                                 Long bookId) {
}
