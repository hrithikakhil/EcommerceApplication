package com.ecomm.project.services;


import com.ecomm.project.dtos.CategoryDto;
import com.ecomm.project.dtos.CategoryResponse;
import com.ecomm.project.models.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto deleteCategory(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
