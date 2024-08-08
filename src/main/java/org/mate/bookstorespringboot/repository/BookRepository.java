package org.mate.bookstorespringboot.repository;

import java.util.Collection;
import org.mate.bookstorespringboot.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "categories")
    Collection<Object> findAll(Specification<Book> bookSpecification);

    Page<Book> findAllByCategoriesId(Long categoryId, Pageable pageable);
}
