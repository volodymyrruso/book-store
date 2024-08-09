package org.mate.bookstorespringboot.controller;

import lombok.RequiredArgsConstructor;
import org.mate.bookstorespringboot.controller.dto.cartitem.CartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.cartitem.UpdateCartItemRequestDto;
import org.mate.bookstorespringboot.controller.dto.shoppingcart.ShoppingCartDto;
import org.mate.bookstorespringboot.model.User;
import org.mate.bookstorespringboot.service.interfaces.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Validated
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping()
    public ResponseEntity<ShoppingCartDto> getUserShoppingCart(Authentication authentication) {
        return ResponseEntity
                .ok(shoppingCartService.getShoppingCart(getUserIdByAuthentication(authentication)));
    }

    @PostMapping()
    public ResponseEntity<ShoppingCartDto> addBookToShoppingCart(Authentication authentication,
                                                                 @RequestBody @Validated
                                                                 CartItemRequestDto requestDto) {
        return ResponseEntity.ok(shoppingCartService
                .addCartItemToShoppingCart(getUserIdByAuthentication(authentication), requestDto));
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<ShoppingCartDto> updateShoppingCart(Authentication authentication,
                                                              @RequestBody @Validated
                                                              UpdateCartItemRequestDto requestDto,
                                                              @PathVariable Long cartItemId) {
        return ResponseEntity.ok(shoppingCartService
                .updateCartItemInShoppingCart(getUserIdByAuthentication(authentication),
                        cartItemId, requestDto));
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteShoppingCart(Authentication authentication,
                                   @PathVariable Long cartItemId) {
        shoppingCartService
                .deleteCartItemFromShoppingCart(getUserIdByAuthentication(authentication),
                        cartItemId);
    }

    private Long getUserIdByAuthentication(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }
}
