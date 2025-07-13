package com.cskrisztian.reciperadar.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name = "meals")
public class Meal {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long id;
    private String name;
    private String alternateName;
    private String category;
    private String area;
    @Column(columnDefinition = "TEXT")
    private String instructions;
    private String imageUrl;
    private String youtubeUrl;
    private String sourceUrl;
    private String strMealThumb;
    private String imageSource;
    private String creativeCommonsConfirmed;
    private String dateModified;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    // Getters and setters
}