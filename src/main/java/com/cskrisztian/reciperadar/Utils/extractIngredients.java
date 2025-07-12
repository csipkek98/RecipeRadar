package com.cskrisztian.reciperadar.Utils;

import java.util.ArrayList;
import java.util.List;

import com.cskrisztian.reciperadar.DTO.IngredientMeasureDTO;
import com.cskrisztian.reciperadar.DTO.MealDTO;

public class extractIngredients {
    public List<IngredientMeasureDTO> extractIngredientsFromDTO(MealDTO dto) {
        List<IngredientMeasureDTO> result = new ArrayList<>();
    
        for (int i = 1; i <= 20; i++) {
            try {
                String ingredient = (String) MealDTO.class.getDeclaredMethod("getStrIngredient" + i).invoke(dto);
                String measure = (String) MealDTO.class.getDeclaredMethod("getStrMeasure" + i).invoke(dto);
    
                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    result.add(new IngredientMeasureDTO(ingredient.trim(), measure != null ? measure.trim() : ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception as needed, e.g., log it or rethrow it
                // Log or handle reflection errors
            }
        }
    
        return result;
    }
}
