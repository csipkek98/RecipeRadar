package com.cskrisztian.reciperadar.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CategoriesWrapperDTO {
    
    private List<CategoriesDTO> categories;

    // Getters and setters can be omitted due to Lombok @Data annotation   
}
