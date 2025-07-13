package com.cskrisztian.reciperadar.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Categorie {

    @Id
    @Column(name = "category_id")
    private Long id;
    private String category_name;
    private String category_thumbnail_url;
    @Column(columnDefinition = "TEXT")
    private String category_description;

}
