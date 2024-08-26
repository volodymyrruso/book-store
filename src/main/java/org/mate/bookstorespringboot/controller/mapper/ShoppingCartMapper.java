package org.mate.bookstorespringboot.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mate.bookstorespringboot.configuration.MapperConfig;
import org.mate.bookstorespringboot.controller.dto.shoppingcart.ShoppingCartDto;
import org.mate.bookstorespringboot.model.ShoppingCart;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "cartItems", source = "cartItem")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
