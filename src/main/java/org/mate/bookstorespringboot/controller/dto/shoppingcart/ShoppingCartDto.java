package org.mate.bookstorespringboot.controller.dto.shoppingcart;

import java.util.Set;
import lombok.Data;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemDto;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
