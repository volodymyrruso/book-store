CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role ENUM('USER', 'ADMIN') NOT NULL UNIQUE
);
