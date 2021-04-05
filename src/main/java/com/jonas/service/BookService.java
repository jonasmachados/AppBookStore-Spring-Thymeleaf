package com.jonas.service;

import com.jonas.domain.Book;
import com.jonas.domain.Category;
import com.jonas.exception.RecordNotFoundException;
import com.jonas.repositories.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Book getBookById(Integer id) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new RecordNotFoundException("No category record exist for given id");
        }
    }

    public Book createOrUpdateBook(Book entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<Book> book = repository.findById(entity.getId());

            if (book.isPresent()) {
                Book newBook = book.get();
                newBook.setTitle(entity.getTitle());
                newBook.setAuthorName(entity.getAuthorName());
                newBook.setText(entity.getText());
                newBook.setCategory(entity.getCategory());

                newBook = repository.save(newBook);

                return newBook;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteBookById(Integer id) throws RecordNotFoundException {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No book record exist for given id");
        }
    }
}
