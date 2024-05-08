package org.mate.bookstorespringboot.controller.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookRequestDto {

    private String title;
    private String author;
    private String description;
    private BigDecimal price;
    private String isbn;
    private String coverImage;
}
