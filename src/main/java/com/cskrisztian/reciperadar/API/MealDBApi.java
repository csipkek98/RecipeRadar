package com.cskrisztian.reciperadar.API;

import com.cskrisztian.reciperadar.DTO.CategoriesWrapperDTO;
import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.DTO.MealsWrapperDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MealDBApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public MealsWrapperDTO getMealsByCategory(String category) {
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + category;
        ResponseEntity<MealsWrapperDTO> response = restTemplate.getForEntity(url, MealsWrapperDTO.class);
        return response.getBody();
    }

    public MealDTO getMealById(String id) {
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
        ResponseEntity<MealsWrapperDTO> response = restTemplate.getForEntity(url, MealsWrapperDTO.class);
        MealsWrapperDTO meals = response.getBody();
        if (meals == null || meals.getMeals().size() == 0) {
            return null; // No meal found for the given ID
        } else {
            return meals.getMeals().get(0); // Return the first meal found
        }
    }   

    public MealsWrapperDTO getAllMeals() {
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        ResponseEntity<MealsWrapperDTO> response = restTemplate.getForEntity(url, MealsWrapperDTO.class);
        return response.getBody();
    }

    public CategoriesWrapperDTO getAllCategories() {
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        ResponseEntity<CategoriesWrapperDTO> response = restTemplate.getForEntity(url, CategoriesWrapperDTO.class);
        return response.getBody();
    }
}

