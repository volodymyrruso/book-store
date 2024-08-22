package org.mate.bookstorespringboot.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.book.BookDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.UpdateCartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.shoppingcart.ShoppingCartDto;
import org.mate.bookstorespringboot.controller.mapper.BookMapper;
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
import org.mate.bookstorespringboot.service.interfaces.BookService;
import org.mate.bookstorespringboot.service.interfaces.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartIServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Override
    public ShoppingCartDto getShoppingCart(Long userId) {
        ShoppingCart cart = getShoppingCartByUserId(userId);
        return shoppingCartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public ShoppingCartDto addCartItemToShoppingCart(Long userId, CartItemRequestDto cartItem) {
        ShoppingCart shoppingCartToBeSaved = getShoppingCartByUserId(userId);
        BookDto bookDto = bookService.findById(cartItem.bookId());
        Book book = bookMapper.dtoToEntity(bookDto);

        Optional<CartItem> existingCartItem = cartItemRepository
                .findByShoppingCartIdAndBookId(shoppingCartToBeSaved.getId(),
                        cartItem.bookId());

        if (existingCartItem.isPresent()) {
            CartItem updatedCartItem = existingCartItem.get();
            updatedCartItem.setQuantity(updatedCartItem.getQuantity() + cartItem.quantity());
            cartItemRepository.save(updatedCartItem);
        } else {
            CartItem newCartItem = cartItemMapper.toEntity(cartItem);
            newCartItem.setBook(book);
            newCartItem.setShoppingCart(shoppingCartToBeSaved);
            cartItemRepository.save(newCartItem);
            shoppingCartToBeSaved.getCartItem().add(newCartItem);
        }

        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCartToBeSaved));
    }

    @Override
    @Transactional
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
                        }).orElseThrow(() ->
                                new EntityNotFoundException("Cart item not found with id: "
                                + cartItemId));
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    @Transactional
    public void deleteCartItemFromShoppingCart(Long userId, Long cartItemId) {

        ShoppingCart shoppingCart = getShoppingCartByUserId(userId);
        CartItem cartItem = getCartItemById(cartItemId);

        shoppingCart.getCartItem().remove(cartItem);
        cartItem.setShoppingCart(shoppingCart);

        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional
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
                .orElseThrow(() ->
                        new EntityNotFoundException("Cart item not found with id: " + cartItemId));
    }
}
