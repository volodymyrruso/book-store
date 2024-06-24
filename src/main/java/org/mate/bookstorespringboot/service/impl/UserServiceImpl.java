package org.mate.bookstorespringboot.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.UserRequestDto;
import org.mate.bookstorespringboot.controller.dto.UserResponseDto;
import org.mate.bookstorespringboot.controller.mapper.UserMapper;
import org.mate.bookstorespringboot.exceptions.RegistrationException;
import org.mate.bookstorespringboot.model.Role;
import org.mate.bookstorespringboot.model.User;
import org.mate.bookstorespringboot.repository.RoleRepository;
import org.mate.bookstorespringboot.repository.UserRepository;
import org.mate.bookstorespringboot.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String CANT_FIND_ROLE_BY_NAME = "Can't find role by name: %s";
    private static final String USER_ALREADY_EXISTS = "User with such email: %s already exists";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDto.email());
        if (optionalUser.isPresent()) {
            throw new RegistrationException(USER_ALREADY_EXISTS
                    .formatted(optionalUser.get().getEmail()));
        }
        Role userRole = roleRepository
                .findByRoleName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new RegistrationException(CANT_FIND_ROLE_BY_NAME
                        .formatted(Role.RoleName.ROLE_USER.name())));
        Set<Role> defaultUserRoleSet = new HashSet<>();
        defaultUserRoleSet.add(userRole);

        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        user.setRole(defaultUserRoleSet);
        return userMapper.toDto(userRepository.save(user));
    }
}
