package com.cskrisztian.reciperadar.Controllers;

import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.DTO.MealsWrapperDTO;
import com.cskrisztian.reciperadar.Services.APIServices;
import com.cskrisztian.reciperadar.Services.RecipeService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final RecipeService recipeService;
    private final APIServices apiServices;

    public MealController(RecipeService recipeService, APIServices apiServices) {
        this.recipeService = recipeService;
        this.apiServices = apiServices;
    }

    @GetMapping("/initDB")
    public void initDBwithData() {
        apiServices.initDBwithData();
    }

    @RequestMapping(value="/get_all_meals", produces = "application/json")
    public MealsWrapperDTO getAllMeals() {
        return recipeService.getAllMeals();
    }

    @RequestMapping(value="/meal_detail/{id}", produces = "application/json")
    public MealDTO getMealById(@PathVariable String id) {
        return recipeService.findById(id);
        // if (mealDTO != null) {
        //     return ResponseEntity.ok(MealDTOtoEntity(mealDTO));
        // } else {
        //     return ResponseEntity.notFound().build();
        // }
    }
}
