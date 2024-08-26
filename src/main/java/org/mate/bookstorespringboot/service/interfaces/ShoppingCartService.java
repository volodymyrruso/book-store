package org.mate.bookstorespringboot.service.interfaces;

import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.UpdateCartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.shoppingcart.ShoppingCartDto;
import org.mate.bookstorespringboot.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCartDto getShoppingCart(Long userId);

    ShoppingCartDto addCartItemToShoppingCart(Long userId, CartItemRequestDto cartItem);

    ShoppingCartDto updateCartItemInShoppingCart(Long userId,
                                                 Long cartItemId,
                                                 UpdateCartItemRequestDto cartItem);

    void deleteCartItemFromShoppingCart(Long userId, Long cartItemId);

    ShoppingCart createShoppingCart(Long userId);
}
