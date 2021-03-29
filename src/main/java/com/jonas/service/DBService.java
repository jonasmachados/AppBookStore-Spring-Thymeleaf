package com.jonas.service;

import com.jonas.domain.Book;
import com.jonas.domain.Category;
import com.jonas.repositories.BookRepository;
import com.jonas.repositories.CategoryRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonas, created 28/03/2021
 */
@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

    public void instanciaBaseDeDados() {
        Category cat1 = new Category(null, "Informática", "Books de informatica");
        Category cat2 = new Category(null, "Ficção Científica", "Ficção Científica");
        Category cat3 = new Category(null, "Biografias", "Books de Biografias");

        Book l1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
        Book l2 = new Book(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", cat1);
        Book l3 = new Book(null, "The Time Machine", "H.G. Wells", "Lorem ipsum", cat2);
        Book l4 = new Book(null, "The War of the Worlds", "H.G. Wells", "Lorem ipsum", cat2);
        Book l5 = new Book(null, "I, Robot", "Isaac Asimov", "Lorem ipsum", cat2);

        cat1.getBooks().addAll(Arrays.asList(l1, l2));
        cat2.getBooks().addAll(Arrays.asList(l3, l4, l5));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        bookRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
    }

}
