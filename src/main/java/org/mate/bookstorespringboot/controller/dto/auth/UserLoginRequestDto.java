package org.mate.bookstorespringboot.controller.dto.auth;

import org.mate.bookstorespringboot.validation.annotations.ValidEmail;
import org.mate.bookstorespringboot.validation.annotations.ValidPassword;

public record UserLoginRequestDto(@ValidEmail String email,
                                  @ValidPassword String password) {
}
