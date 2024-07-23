package org.mate.bookstorespringboot.controller.dto.book;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record BookDto(Long id,
                      String title,
                      String author,
                      String isbn,
                      BigDecimal price,
                      String description,
                      String coverImage) {
}
