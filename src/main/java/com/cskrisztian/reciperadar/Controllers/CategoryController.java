package com.cskrisztian.reciperadar.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cskrisztian.reciperadar.DTO.CategoriesWrapperDTO;
import com.cskrisztian.reciperadar.Services.RecipeService;

@RestController
public class CategoryController {
    
    private final RecipeService recipeService;

    public CategoryController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    
    @GetMapping("/api/categories/get_all_categories")
    public CategoriesWrapperDTO getAllCategories() {
        // Retrieve all categories from the database or external API
        return recipeService.getAllCategories();
    }
}
