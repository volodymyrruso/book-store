package org.mate.bookstorespringboot.service.impl;

import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.UpdateCartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.shoppingcart.ShoppingCartDto;
import org.mate.bookstorespringboot.controller.mapper.CartItemMapper;
import org.mate.bookstorespringboot.controller.mapper.ShoppingCartMapper;
import org.mate.bookstorespringboot.exceptions.EntityNotFoundException;
import org.mate.bookstorespringboot.model.Book;
import org.mate.bookstorespringboot.model.CartItem;
import org.mate.bookstorespringboot.model.ShoppingCart;
import org.mate.bookstorespringboot.model.User;
import org.mate.bookstorespringboot.repository.BookRepository;
import org.mate.bookstorespringboot.repository.CartItemRepository;
import org.mate.bookstorespringboot.repository.ShoppingCartRepository;
import org.mate.bookstorespringboot.service.interfaces.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartIServiceImpl implements ShoppingCartService {

    private static final String SHOPPING_CART_NOT_FOUND = "Shopping cart not found with id: ";
    private static final String CART_ITEM_NOT_FOUND = "Cart item not found with id: ";
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto getShoppingCart(Long userId) {
        ShoppingCart cart = getShoppingCartByUserId(userId);
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public ShoppingCartDto addCartItemToShoppingCart(Long userId,
                                                     CartItemRequestDto cartItem) {
        ShoppingCart shoppingCartToBeSaved = getShoppingCartByUserId(userId);

        Book book = bookRepository.findById(cartItem.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: "
                        + cartItem.bookId()));

        cartItemRepository.findByShoppingCartIdAndBookId(shoppingCartToBeSaved.getId(),
                        cartItem.bookId())
                .ifPresent(cartItem1 -> {
                    throw new EntityNotFoundException("Book with id: "
                            + cartItem.bookId()
                            + " already in shopping cart");
                });

        CartItem cartItems = cartItemMapper.toEntity(cartItem);
        cartItems.setBook(book);
        cartItems.setShoppingCart(shoppingCartToBeSaved);
        cartItemRepository.save(cartItems);

        shoppingCartToBeSaved.getCartItem().add(cartItems);

        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCartToBeSaved));
    }

    @Override
    public ShoppingCartDto updateCartItemInShoppingCart(Long userId,
                                                        Long cartItemId,
                                                        UpdateCartItemRequestDto
                                                                    updateCartItemRequestDto) {
        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        CartItem cartItem =
                cartItemRepository.findByShoppingCartIdAndBookId(cartItemId, shoppingCart.getId())
                        .map(item -> {
                            item.setQuantity(updateCartItemRequestDto.quantity());
                            return item;
                        })
                        .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                                String.format("No cart item with id: %d for user: %d",
                                        cartItemId, userId)
                        ));
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public void deleteCartItemFromShoppingCart(Long userId, Long cartItemId) {

        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        CartItem cartItem = getCartItemById(cartItemId);

        shoppingCart.getCartItem().remove(cartItem);
        cartItem.setShoppingCart(shoppingCart);

        cartItemRepository.delete(cartItem);
    }

    @Override
    public ShoppingCart createShoppingCart(Long userId) {
        User user = new User();
        user.setId(userId);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> createShoppingCart(userId));
    }

    private CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(CART_ITEM_NOT_FOUND + cartItemId));
    }
}
