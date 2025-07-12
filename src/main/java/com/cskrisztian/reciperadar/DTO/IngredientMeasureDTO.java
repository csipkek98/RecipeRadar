package com.cskrisztian.reciperadar.DTO;

import lombok.Data;

@Data
public class IngredientMeasureDTO {
    private String ingredient;
    private String measure;

    public IngredientMeasureDTO(String ingredient, String measure) {
        this.ingredient = ingredient;
        this.measure = measure;
    }

    // Getters and setters
}
