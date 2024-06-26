package org.mate.bookstorespringboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.auth.UserLoginRequestDto;
import org.mate.bookstorespringboot.controller.dto.auth.UserLoginResponseDto;
import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationRequestDto;
import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationResponseDto;
import org.mate.bookstorespringboot.exceptions.RegistrationException;
import org.mate.bookstorespringboot.security.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponseDto> register(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        UserRegistrationResponseDto user = userService.createUser(userRegistrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
