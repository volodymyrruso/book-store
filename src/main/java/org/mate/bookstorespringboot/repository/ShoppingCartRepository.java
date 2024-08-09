package org.mate.bookstorespringboot.repository;

import java.util.Optional;
import org.mate.bookstorespringboot.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("""
            SELECT sc FROM ShoppingCart sc
             LEFT JOIN FETCH sc.cartItem ci
            LEFT JOIN FETCH sc.cartItem.book
            WHERE sc.user.id = :userId"""
    )
    Optional<ShoppingCart> findByUserId(Long userId);
}
