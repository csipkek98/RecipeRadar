package com.cskrisztian.reciperadar.Controllers;

import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.DTO.MealsWrapperDTO;
import com.cskrisztian.reciperadar.Services.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/api/meals/get_all_meals")
public class MealController {

    private final RecipeService recipeService;

    public MealController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value="/api/meals/get_all_meals", produces = "application/json")
    public MealsWrapperDTO getAllMeals() {
        return recipeService.getAllMeals();
    }

    @RequestMapping(value="/api/meals/meal_detail/{id}", produces = "application/json")
    public MealDTO getMealById(@PathVariable String id) {
        MealDTO mealDTO = recipeService.findById(id);
        return mealDTO;
        // if (mealDTO != null) {
        //     return ResponseEntity.ok(MealDTOtoEntity(mealDTO));
        // } else {
        //     return ResponseEntity.notFound().build();
        // }
    }
}
