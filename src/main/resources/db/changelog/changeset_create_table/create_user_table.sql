CREATE TABLE users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    email VARCHAR(40) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    shipping_address VARCHAR(255)
);
