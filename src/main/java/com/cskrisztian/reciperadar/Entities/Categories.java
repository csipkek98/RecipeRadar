package com.cskrisztian.reciperadar.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Categories {

    @Id
    @Column(name = "category_id")
    private Long id;
    private String category_name;
    private String category_thumbnail_url;
    @Column(columnDefinition = "TEXT")
    private String category_description;

}
