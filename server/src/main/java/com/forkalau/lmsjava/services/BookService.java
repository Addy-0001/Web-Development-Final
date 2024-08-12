package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Author;
import com.forkalau.lmsjava.domain.Book;
import com.forkalau.lmsjava.repositories.IAuthorRepository;
import com.forkalau.lmsjava.repositories.IBookRepository;
import com.forkalau.lmsjava.services.iservices.IBookService;
import com.forkalau.lmsjava.services.iservices.IFactory;
import com.forkalau.lmsjava.services.iservices.IWriteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private IWriteLog writeLog;

    @Autowired
    private IFactory factory;

    @Override
    public Book saveOrUpdateBook(Book book, String authors) {
        try {
            AddAuthorHandler(book, authors);
            writeLog.saveLog("done");
            Book book1 = bookRepository.save(book);
            return book1;
        } catch (Exception e) {
            throw factory.ThrowException("Book ISBN: '" + book.getIsbn() + "' already exist");
        }
    }

    public void AddAuthorHandler(Book book, String authors) {
        Set<String> authorSet = Arrays.stream(authors.split(" ,")).collect(Collectors.toSet());
        for (String authorName : authorSet) {
            Author author1 = factory.CreateAuthor();
            author1.setName(authorName);
            book.addAuthors(author1);
        }
    }
}
