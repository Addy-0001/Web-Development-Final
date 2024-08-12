package com.forkalau.lmsjava.services.iservices;

import com.forkalau.lmsjava.domain.Book;

public interface IBookService {
    Book saveOrUpdateBook(Book book, String authors);
}
