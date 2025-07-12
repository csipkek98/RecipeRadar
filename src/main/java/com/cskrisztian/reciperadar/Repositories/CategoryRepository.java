package com.cskrisztian.reciperadar.Repositories;

import com.cskrisztian.reciperadar.Entities.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Categories, Long> {
    // This interface will automatically provide CRUD operations for Categories entity
    // No additional methods are needed unless specific queries are required
    
}
