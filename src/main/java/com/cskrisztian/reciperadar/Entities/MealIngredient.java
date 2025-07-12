package com.cskrisztian.reciperadar.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meal_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id", referencedColumnName = "meal_id")
    private Meals meal;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private String measure;

    // Getters and setters
}
