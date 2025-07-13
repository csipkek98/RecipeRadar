package com.cskrisztian.reciperadar.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Collections;

import com.cskrisztian.reciperadar.DTO.IngredientMeasureDTO;
import com.cskrisztian.reciperadar.DTO.MealDTO;
import com.cskrisztian.reciperadar.Entities.Ingredient;
import com.cskrisztian.reciperadar.Entities.Meal;
import com.cskrisztian.reciperadar.Entities.Tag;

public class MealMapper {

     public static Meal toEntity(MealDTO dto) {
        Meal meal = new Meal();

        meal.setId(Long.parseLong(dto.getIdMeal()));
        meal.setName(dto.getStrMeal());
        meal.setAlternateName(dto.getStrMealAlternate());
        meal.setCategory(dto.getStrCategory());
        meal.setArea(dto.getStrArea());
        meal.setInstructions(dto.getStrInstructions());
        meal.setStrMealThumb(dto.getStrMealThumb());
        meal.setYoutubeUrl(dto.getStrYoutube());
        meal.setSourceUrl(dto.getStrSource());
        meal.setImageSource(dto.getStrImageSource());
        meal.setCreativeCommonsConfirmed(dto.getStrCreativeCommonsConfirmed());
        meal.setDateModified(dto.getDateModified());

        // Handle ingredients
        List<Ingredient> ingredients = extractIngredients(dto, meal);
        meal.setIngredients(ingredients);

        // Handle tags (comma separated)
        List<Tag> tags = extractTags(dto.getStrTags(), meal);
        meal.setTags(tags);

        return meal;
    }

    public static MealDTO toDTO(Meal meal) {
        MealDTO dto = new MealDTO();

        dto.setIdMeal(String.valueOf(meal.getId()));
        dto.setStrMeal(meal.getName());
        dto.setStrMealAlternate(meal.getAlternateName());
        dto.setStrCategory(meal.getCategory());
        dto.setStrArea(meal.getArea());
        dto.setStrInstructions(meal.getInstructions());
        dto.setStrMealThumb(meal.getStrMealThumb());
        dto.setStrYoutube(meal.getYoutubeUrl());
        dto.setStrSource(meal.getSourceUrl());
        dto.setStrImageSource(meal.getImageSource());
        dto.setStrCreativeCommonsConfirmed(meal.getCreativeCommonsConfirmed());
        dto.setDateModified(meal.getDateModified());

        // Handle ingredients
        List<IngredientMeasureDTO> ingredientMeasureDTOS = meal.getIngredients().stream()
                .map(ing -> new IngredientMeasureDTO(ing.getName(), ing.getMeasure()))
                .collect(Collectors.toList());
        dto.setIngredientsAndMeasures(ingredientMeasureDTOS);

        // Handle tags
        String strTags = meal.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.joining(","));
        dto.setStrTags(strTags);

        return dto;
    }

    private static List<Ingredient> extractIngredients(MealDTO dto, Meal meal) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            try {
                String ing = (String) MealDTO.class.getDeclaredMethod("getStrIngredient" + i).invoke(dto);
                String measure = (String) MealDTO.class.getDeclaredMethod("getStrMeasure" + i).invoke(dto);

                if (ing != null && !ing.isBlank()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(ing.trim());
                    ingredient.setMeasure(measure != null ? measure.trim() : "");
                    ingredient.setMeal(meal);
                    ingredients.add(ingredient);
                }

            } catch (Exception e) {

                // Optional: log error
            }
        }

        return ingredients;
    }

    private static List<Tag> extractTags(String strTags, Meal meal) {
        if (strTags == null || strTags.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(strTags.split(","))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .map(tagStr -> {
                    Tag tag = new Tag();
                    tag.setName(tagStr);
                    tag.setMeal(meal);
                    return tag;
                })
                .collect(Collectors.toList());
    }
}

