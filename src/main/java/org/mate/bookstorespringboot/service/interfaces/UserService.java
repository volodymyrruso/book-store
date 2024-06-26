package org.mate.bookstorespringboot.service.interfaces;

import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationRequestDto;
import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationResponseDto;

public interface UserService {

    UserRegistrationResponseDto createUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
