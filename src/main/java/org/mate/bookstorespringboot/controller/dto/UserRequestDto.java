package org.mate.bookstorespringboot.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.mate.bookstorespringboot.validation.annotations.FieldMatch;
import org.mate.bookstorespringboot.validation.annotations.ValidEmail;
import org.mate.bookstorespringboot.validation.annotations.ValidPassword;

@FieldMatch(first = "password", second = "repeatPassword")
public record UserRequestDto(@ValidEmail String email,
                             @ValidPassword String password,
                             @NotBlank String repeatPassword,
                             @NotBlank @Max(50) String firstName,
                             @NotBlank @Max(50) String lastName,
                             String shippingAddress) {
}
