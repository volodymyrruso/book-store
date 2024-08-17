package org.mate.bookstorespringboot.repository;

import java.util.Optional;
import org.mate.bookstorespringboot.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT ci FROM CartItem ci "
            + "JOIN FETCH ci.book b "
            + "WHERE ci.shoppingCart.id = :shoppingCartId "
            + "AND ci.book.id = :bookId")
    Optional<CartItem> findByShoppingCartIdAndBookId(Long shoppingCartId,
                                                     Long bookId);
}
