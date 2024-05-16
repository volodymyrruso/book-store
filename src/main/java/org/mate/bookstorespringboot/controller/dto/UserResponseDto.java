package org.mate.bookstorespringboot.controller.dto;

public record UserResponseDto(Long id,
                              String email,
                              String firstName,
                              String lastName,
                              String shippingAddress) {
}
