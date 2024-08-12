package com.forkalau.lmsjava.repositories;

import com.forkalau.lmsjava.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    Set<Book>  findByTitleContaining(String title);

}