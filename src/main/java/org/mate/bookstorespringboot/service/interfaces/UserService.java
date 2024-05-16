package org.mate.bookstorespringboot.service.interfaces;

import org.mate.bookstorespringboot.controller.dto.UserRequestDto;
import org.mate.bookstorespringboot.controller.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
}
