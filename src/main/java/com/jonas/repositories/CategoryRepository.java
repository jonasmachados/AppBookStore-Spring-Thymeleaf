package com.jonas.repositories;

import com.jonas.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonas, cretaed 28/03/2021
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
