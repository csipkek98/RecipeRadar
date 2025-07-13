package com.cskrisztian.reciperadar.Utils;

import com.cskrisztian.reciperadar.DTO.CategoriesDTO;
import com.cskrisztian.reciperadar.Entities.Categorie;

public class CategoryMapper {
    public static Categorie toEntity(CategoriesDTO categoriesDTO) {
        if (categoriesDTO == null) {
            return null;
        }
        Categorie category = new Categorie();
        category.setId(Long.parseLong(categoriesDTO.getIdCategory()));
        category.setCategory_name(categoriesDTO.getStrCategory());
        category.setCategory_thumbnail_url(categoriesDTO.getStrCategoryThumb());
        category.setCategory_description(categoriesDTO.getStrCategoryDescription());
        return category;
    }

    public static CategoriesDTO toDTO(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        CategoriesDTO categoriesDTO = new CategoriesDTO();
        categoriesDTO.setIdCategory(String.valueOf(categorie.getId()));
        categoriesDTO.setStrCategory(categorie.getCategory_name());
        categoriesDTO.setStrCategoryThumb(categorie.getCategory_thumbnail_url());
        categoriesDTO.setStrCategoryDescription(categorie.getCategory_description());
        return categoriesDTO;
    }
}
