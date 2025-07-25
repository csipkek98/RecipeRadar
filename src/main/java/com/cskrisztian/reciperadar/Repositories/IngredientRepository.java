package com.cskrisztian.reciperadar.Repositories;

import com.cskrisztian.reciperadar.Entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
