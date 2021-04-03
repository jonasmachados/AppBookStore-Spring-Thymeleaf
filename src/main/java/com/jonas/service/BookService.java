package com.jonas.service;

import com.jonas.domain.Book;
import com.jonas.repositories.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonas
 */
@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> findAllBook() {
        List<Book> result = (List<Book>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Book>();
        }
    }
}
