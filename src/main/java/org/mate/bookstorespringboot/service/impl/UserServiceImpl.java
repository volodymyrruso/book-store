package org.mate.bookstorespringboot.service.impl;

import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.UserRequestDto;
import org.mate.bookstorespringboot.controller.dto.UserResponseDto;
import org.mate.bookstorespringboot.controller.mapper.UserMapper;
import org.mate.bookstorespringboot.exceptions.RegistrationException;
import org.mate.bookstorespringboot.model.Role;
import org.mate.bookstorespringboot.model.User;
import org.mate.bookstorespringboot.model.enums.RoleName;
import org.mate.bookstorespringboot.repository.RoleRepository;
import org.mate.bookstorespringboot.repository.UserRepository;
import org.mate.bookstorespringboot.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.email());
        if (optionalUser.isPresent()) {
            throw new RegistrationException("User with such email: %s already exists"
                    .formatted(optionalUser.get().getEmail()));
        }
        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        Optional<Role> defaultRole = roleRepository.findByRoleName(RoleName.USER);
        user.setRole(Collections.singleton(defaultRole.get()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
