package org.mate.bookstorespringboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.UserRequestDto;
import org.mate.bookstorespringboot.controller.dto.UserResponseDto;
import org.mate.bookstorespringboot.exceptions.RegistrationException;
import org.mate.bookstorespringboot.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto> register(
            @RequestBody @Valid UserRequestDto userRequestDto)
            throws RegistrationException {
        UserResponseDto user = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
