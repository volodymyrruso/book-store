package org.mate.bookstorespringboot.controller.dto.auth;

public record UserRegistrationResponseDto(Long id,
                                          String email,
                                          String firstName,
                                          String lastName,
                                          String shippingAddress) {
}
