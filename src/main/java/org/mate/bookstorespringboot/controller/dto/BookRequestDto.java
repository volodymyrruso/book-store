package org.mate.bookstorespringboot.controller.dto;

import java.math.BigDecimal;

public record BookRequestDto(String title,
                             String author,
                             String description,
                             BigDecimal price,
                             String isbn,
                             String coverImage) {
}
