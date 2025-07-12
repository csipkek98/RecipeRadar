package com.cskrisztian.reciperadar.Services;

import org.springframework.stereotype.Service;
import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.DTO.MealsWrapperDTO;
import com.cskrisztian.reciperadar.DTO.CategoriesDTO;
import com.cskrisztian.reciperadar.DTO.CategoriesWrapperDTO;
import com.cskrisztian.reciperadar.DTO.IngredientMeasureDTO;
import com.cskrisztian.reciperadar.Entities.Categories;
import com.cskrisztian.reciperadar.Entities.Ingredient;
import com.cskrisztian.reciperadar.Entities.Meals;
import com.cskrisztian.reciperadar.Repositories.CategoryRepository;
import com.cskrisztian.reciperadar.Repositories.MealRepository;
import com.cskrisztian.reciperadar.Utils.CategoryMapper;
import com.cskrisztian.reciperadar.Utils.MealMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void saveRecipes(MealsWrapperDTO mealsWrapper) {
        System.out.println("Saving recipes...");
        if (mealsWrapper == null || mealsWrapper.getMeals() == null) {
            System.out.println("No meals to save.");
            return; // or throw an exception
        }

        for (MealDTO mealDTO : mealsWrapper.getMeals()) {
            System.out.println("Processing meal: " + mealDTO.getIdMeal());
            Meals meal = MealMapper.toEntity(mealDTO);  // Convert DTO to Entity

            // Extract and add ingredients to meal
            List<IngredientMeasureDTO> ingredientMeasureDTOS = extractIngredientsFromDTO(mealDTO);
            for (IngredientMeasureDTO ingredientMeasureDTO : ingredientMeasureDTOS) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientMeasureDTO.getIngredient());
                ingredient.setMeasure(ingredientMeasureDTO.getMeasure());
                ingredient.setMeal(meal); // Associate with the meal
                meal.getIngredients().add(ingredient); // Add to meal's ingredient list
            }
            System.out.println("Saving meal: " + meal.getName() + " with ID: " + meal.getId());

            try {
                Optional<Meals> MealInDB = mealRepository.findById(meal.getId());
                if(MealInDB != null && MealInDB.get().getCategory() != null) {
                    System.out.println("Full Meal data already exists in DB with same category, skipping save: " + meal.getName());
                    continue; // Skip saving if meal already exists with same category
                }
                mealRepository.save(meal); // Only save the meal, cascade will handle ingredients
                System.out.println("Saved meal: " + meal.getName());
            } catch (Exception e) {
                System.err.println("Error saving meal: " + meal.getName() + ". Error: " + e.getMessage());
            }
        }
    }

    private List<IngredientMeasureDTO> extractIngredientsFromDTO(MealDTO mealDTO) {
        System.out.println("Extracting ingredients from DTO: " + mealDTO.getIdMeal());
        List<IngredientMeasureDTO> result = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            try {
                String ingredient = (String) mealDTO.getClass().getDeclaredMethod("getStrIngredient" + i).invoke(mealDTO);
                String measure = (String) mealDTO.getClass().getDeclaredMethod("getStrMeasure" + i).invoke(mealDTO);
                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    result.add(new IngredientMeasureDTO(ingredient.trim(), measure != null ? measure.trim() : ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Ignore missing ingredient fields
            }
        }
        System.out.println("Extracted " + result.size() + " ingredients from DTO: " + mealDTO.getIdMeal());
        return result;
    }

    public void saveRecipe(MealDTO mealDTO) {
        // Meal meal = new Meal();
        // meal.setName()
        Meals meal = MealMapper.toEntity(mealDTO);  // Convert DTO to Entity
        System.out.println("Saving recipe: " + meal.getName());
        try {
            mealRepository.save(meal);
            System.out.println("Recipe saved successfully: " + meal.getName());
        } catch (Exception e) {
            System.err.println("Error saving recipe: " + meal.getName() + ". Error: " + e.getMessage());
        }
    }

    public void saveCategory(CategoriesDTO categoriesDTO) {
        // Implement saving logic for categories if needed
        Categories category = CategoryMapper.toEntity(categoriesDTO); // Assuming you have a CategoriesMapper similar to MealMapper
        System.out.println("Saving category: " + categoriesDTO.getStrCategory());
        categoryRepository.save(category);

    }

    public void saveCategories(CategoriesWrapperDTO categoriesWrapper) {
        if (categoriesWrapper == null || categoriesWrapper.getCategories() == null) {
            System.out.println("No categories to save.");
            return; // or throw an exception
        }

        for (CategoriesDTO categoriesDTO : categoriesWrapper.getCategories()) {
            System.out.println("Processing category: " + categoriesDTO.getStrCategory());
            saveCategory(categoriesDTO);
        }
    }
}