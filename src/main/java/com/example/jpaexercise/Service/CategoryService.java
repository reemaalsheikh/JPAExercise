package com.example.jpaexercise.Service;

import com.example.jpaexercise.Model.Category;
import com.example.jpaexercise.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public boolean UpdateCategory(Integer id,Category category) {
      Category oldCategory = categoryRepository.findById(id).get();

      if (oldCategory == null) {
          return false;
      }
      oldCategory.setCategoryName(category.getCategoryName());
      categoryRepository.save(oldCategory);
      return true;
    }
    public boolean DeleteCategory(Integer id) {
     Category oldCategory = categoryRepository.findById(id).get();
     if (oldCategory == null) {
         return false;
     }
     categoryRepository.delete(oldCategory);
     return true;

    }
}
