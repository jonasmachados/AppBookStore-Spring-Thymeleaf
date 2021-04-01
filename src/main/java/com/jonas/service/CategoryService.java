package com.jonas.service;

import com.jonas.domain.Category;
import com.jonas.exception.RecordNotFoundException;
import com.jonas.repositories.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
     public Category getCategoryById(Integer id) throws RecordNotFoundException {
        Optional<Category> category = repository.findById(id);

        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("No category record exist for given id");
        }
    }

    public Category createOrUpdateCategory(Category entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<Category> category = repository.findById(entity.getId());

            if (category.isPresent()) {
                Category newCategory = category.get();
                newCategory.setName(entity.getName());
                newCategory.setDescription(entity.getDescription()); 

                newCategory = repository.save(newCategory);

                return newCategory;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

}
