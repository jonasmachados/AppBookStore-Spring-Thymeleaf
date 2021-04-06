package com.jonas.resource;

import com.jonas.domain.Category;
import com.jonas.exception.RecordNotFoundException;
import com.jonas.service.CategoryService;
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
 * @author Jonas, created 29/03/2021
 */
@Controller
@RequestMapping(value = "/")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/categories")
    public String findAllCategory(Model model) {
        List<Category> list = service.findAllCategory();
        model.addAttribute("categories", list);
        return "category/list-category";
    }

    @RequestMapping(path = {"/edit", "/edit{id}"})
    public String editCategoryById(Model model, @PathVariable("id") Optional<Integer> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            Category entity = service.getCategoryById(id.get());
            model.addAttribute("category", entity);
        } else {
            model.addAttribute("category", new Category());
        }
        return "category/add-edit-category";
    }

    @RequestMapping(path = "/createCategory", method = RequestMethod.POST)
    public String createOrUpdateCategory(Category category) {
        service.createOrUpdateCategory(category);
        return "redirect:/categories"; //REDIRECT: back to previous HTML.
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteCategoryById(Model model, @PathVariable("id") Integer id)
            throws RecordNotFoundException {
        service.deleteCategoryById(id);
        return "redirect:/categories";
    }
}
