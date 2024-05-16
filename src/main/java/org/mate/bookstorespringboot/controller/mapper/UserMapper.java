package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.UserRequestDto;
import org.mate.bookstorespringboot.controller.dto.UserResponseDto;
import org.mate.bookstorespringboot.model.User;

@Mapper(componentModel = "spring",config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User user);
}
