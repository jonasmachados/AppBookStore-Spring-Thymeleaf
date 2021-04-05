package com.jonas.resource;

import com.jonas.domain.Book;
import com.jonas.exception.RecordNotFoundException;
import com.jonas.service.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(path = {"/editBook", "/editBook/{id}"})
    public String editBookById(Model model, @PathVariable("id") Optional<Integer> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            Book entity = service.getBookById(id.get());
            model.addAttribute("book", entity);
        } else {
            model.addAttribute("book", new Book());
        }
        return "book/add-edit-book";
    }

    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    public String createOrUpdateBook(Book book) {
        service.createOrUpdateBook(book);
        return "redirect:/books"; //REDIRECT: back to previous HTML.
    }
    
    @RequestMapping(path = "/deleteBook/{id}")
    public String deleteBookById(Model model, @PathVariable("id") Integer id)
            throws RecordNotFoundException {
        service.deleteBookById(id);
        return "redirect:/books"; //REDIRECT: back to previous HTML.
    }
      
}
