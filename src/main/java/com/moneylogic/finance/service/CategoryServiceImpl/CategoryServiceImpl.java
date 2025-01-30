package com.moneylogic.finance.service.CategoryServiceImpl;

import com.moneylogic.finance.model.Category;
import com.moneylogic.finance.repository.Category.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        try {
            return categoryRepository.getById(id);
        }
        catch (EntityNotFoundException e)
        {
            return null;
        }
    }

    @Override
    public Category getCategoryByUserId(Long id) {
        return categoryRepository.findCategoryByUserId(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
