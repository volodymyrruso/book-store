CREATE TABLE Books (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      isbn VARCHAR(255) UNIQUE NOT NULL,
                      price DECIMAL(10, 2) NOT NULL,
                      description TEXT,
                      cover_image VARCHAR(255)
);
