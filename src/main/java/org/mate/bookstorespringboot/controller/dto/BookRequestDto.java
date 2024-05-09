package org.mate.bookstorespringboot.controller.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookRequestDto {

    @Nonnull
    private String title;
    @Nonnull
    private String author;
    private String description;
    @Nonnull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    @Nonnull
    private String isbn;
    private String coverImage;
}
