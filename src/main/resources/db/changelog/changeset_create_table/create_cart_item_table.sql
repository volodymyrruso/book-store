CREATE TABLE cart_item (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          shopping_cart_id BIGINT NOT NULL,
                          book_id BIGINT NOT NULL,
                          quantity INT NOT NULL,
                          CONSTRAINT fk_shopping_cart FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id),
                          CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id)
);