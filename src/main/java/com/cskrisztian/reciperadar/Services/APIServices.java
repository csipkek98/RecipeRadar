package com.cskrisztian.reciperadar.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cskrisztian.reciperadar.API.MealDBApi;
import com.cskrisztian.reciperadar.DTO.CategoriesDTO;
import com.cskrisztian.reciperadar.DTO.CategoriesWrapperDTO;
import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.DTO.MealsWrapperDTO;

@Service
public class APIServices {

    @Autowired
    private ApplicationContext context;

    public void initDBwithData(){
        RecipeService recipeService = context.getBean(RecipeService.class);

        MealDBApi mealDBApi = new MealDBApi();
        
        CategoriesWrapperDTO categoriesWrapper = mealDBApi.getAllCategories();

        if (categoriesWrapper != null && categoriesWrapper.getCategories() != null) {
            List<CategoriesDTO> categories = categoriesWrapper.getCategories();
            for (CategoriesDTO category : categories) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Category Name: " + category.getStrCategory());
                getMealsByCategory(category.getStrCategory());
                System.out.println("------------------------------------------------------------------------");
            }
            recipeService.saveCategories(categoriesWrapper);
        }
    }

    public void getMealsByCategory(String category) {
        //ApplicationContext context = SpringApplication.run(ReciperadarApplication.class, args);
        RecipeService recipeService = context.getBean(RecipeService.class);

        MealDBApi mealDBApi = new MealDBApi();

        MealsWrapperDTO mealsWrapper = mealDBApi.getMealsByCategory(category);

        if (mealsWrapper != null && mealsWrapper.getMeals() != null) {
            List<MealDTO> meals = mealsWrapper.getMeals();
            for (MealDTO meal : meals) {
                System.out.println("Meal Name: " + meal.getStrMeal());
                // Process ingredients here
            }
            recipeService.saveRecipes(mealsWrapper);
        }
            
    }

    public void testMealById(String mealId) {
        //ApplicationContext context = SpringApplication.run(ReciperadarApplication.class, args);
        RecipeService recipeService = context.getBean(RecipeService.class);

        MealDBApi mealDBApi = new MealDBApi();
        
        MealDTO mealDTO = mealDBApi.getMealById(mealId);

        if (mealDTO != null) {
            System.out.println("Meal Name: " + mealDTO.getStrMeal());
            recipeService.saveRecipe(mealDTO);
            // Process ingredients here
        }
    }

    public void getAllCategories() {
        //ApplicationContext context = SpringApplication.run(ReciperadarApplication.class, args);
        RecipeService recipeService = context.getBean(RecipeService.class);

        MealDBApi mealDBApi = new MealDBApi();
        
        CategoriesWrapperDTO categoriesWrapper = mealDBApi.getAllCategories();

        if (categoriesWrapper != null && categoriesWrapper.getCategories() != null) {
            List<CategoriesDTO> categories = categoriesWrapper.getCategories();
            for (CategoriesDTO category : categories) {
                System.out.println("Category Name: " + category.getStrCategory());
                // Process category here
            }
            recipeService.saveCategories(categoriesWrapper);
        }
    }
    
}
