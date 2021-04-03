package com.jonas.resource;

import com.jonas.domain.Book;
import com.jonas.domain.Category;
import com.jonas.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jonas
 */
@Controller
@RequestMapping(value = "/")
public class BookResource {

    @Autowired
    private BookService service;

    @RequestMapping(value = "/books")
    public String findAllBook(Model model) {
        List<Book> list = service.findAllBook();
        model.addAttribute("books", list);
        return "book/list-book";
    }
}
