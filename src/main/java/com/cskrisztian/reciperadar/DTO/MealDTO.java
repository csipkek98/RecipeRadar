package com.cskrisztian.reciperadar.DTO;


import java.util.List;

import lombok.Data;

@Data
public class MealDTO {
    private String idMeal;
    private String strMeal;
    private String strMealAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;

    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    private String strSource;
    private String strImageSource;
    private String strCreativeCommonsConfirmed;
    private String dateModified;

    public void setIngredientsAndMeasures(List<IngredientMeasureDTO> mealEntityingredientMeasures) {
        for (int i = 0; i < mealEntityingredientMeasures.size(); i++) {
            if(mealEntityingredientMeasures.get(i).getIngredient() == null){
                continue; // Skip if ingredient is null
            }
            // Set Ingredient and Measure dynamically
            try {

                MealDTO.class.getMethod("setStrIngredient" + (i + 1), String.class)
                    .invoke(this, mealEntityingredientMeasures.get(i).getIngredient());
                MealDTO.class.getMethod("setStrMeasure" + (i + 1), String.class)
                    .invoke(this, mealEntityingredientMeasures.get(i).getMeasure());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
