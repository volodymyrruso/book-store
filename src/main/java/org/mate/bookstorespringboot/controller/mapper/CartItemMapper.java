package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemRequestDto;
import org.mate.bookstorespringboot.model.CartItem;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookName", source = "book.title")
    CartItemDto toDto(CartItem cartItem);

    CartItem toEntity(CartItemRequestDto cartItemRequestDto);
}
