package com.cskrisztian.reciperadar.Repositories;

import com.cskrisztian.reciperadar.Entities.Meals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meals, Long> {
}
