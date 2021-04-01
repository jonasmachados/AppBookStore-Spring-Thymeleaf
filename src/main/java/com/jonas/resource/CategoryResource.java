package com.jonas.resource;

import com.jonas.domain.Category;
import com.jonas.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
