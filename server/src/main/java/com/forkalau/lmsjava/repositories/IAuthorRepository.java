package com.forkalau.lmsjava.repositories;

import com.forkalau.lmsjava.domain.Author;
import com.forkalau.lmsjava.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAuthorRepository extends CrudRepository<Author, Long> {
    Set<Book> findByNameContaining(String author);
}
