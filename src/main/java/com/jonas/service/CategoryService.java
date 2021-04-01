package com.jonas.service;

import com.jonas.domain.Category;
import com.jonas.repositories.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonas, created 29/03/2021
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllCategory() {
        List<Category> result = (List<Category>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Category>();
        }
    }

}
