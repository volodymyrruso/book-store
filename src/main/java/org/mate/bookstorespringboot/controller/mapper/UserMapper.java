package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationRequestDto;
import org.mate.bookstorespringboot.controller.dto.auth.UserRegistrationResponseDto;
import org.mate.bookstorespringboot.model.User;

@Mapper(componentModel = "spring",config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRegistrationRequestDto dto);

    UserRegistrationResponseDto toDto(User user);
}
