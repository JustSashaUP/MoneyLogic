package com.moneylogic.finance.service.CategoryServiceImpl;

import com.moneylogic.finance.model.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category getCategoryByUserId(Long id);

    Category saveCategory(Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategoryById(Long id);
}
