CREATE TABLE shopping_cart (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT,
                              CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);