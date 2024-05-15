package org.mate.bookstorespringboot.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record BookRequestDto(@NotBlank String title,
                             @NotBlank(message = "Author is required") String author,
                             String description,
                             @DecimalMin(value = "0.00",inclusive = false)
                             BigDecimal price,
                             @NotBlank String isbn,
                             String coverImage) {
}
