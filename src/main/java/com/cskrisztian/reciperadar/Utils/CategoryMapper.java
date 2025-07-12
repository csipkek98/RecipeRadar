package com.cskrisztian.reciperadar.Utils;

import com.cskrisztian.reciperadar.DTO.CategoriesDTO;
import com.cskrisztian.reciperadar.Entities.Categories;

public class CategoryMapper {
    public static Categories toEntity(CategoriesDTO categoriesDTO) {
        if (categoriesDTO == null) {
            return null;
        }
        Categories category = new Categories();
        category.setId(Long.parseLong(categoriesDTO.getIdCategory()));
        category.setCategory_name(categoriesDTO.getStrCategory());
        category.setCategory_thumbnail_url(categoriesDTO.getStrCategoryThumb());
        category.setCategory_description(categoriesDTO.getStrCategoryDescription());
        return category;
    }
}
