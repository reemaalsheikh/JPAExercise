package com.example.jpaexercise.Controller;

import com.example.jpaexercise.Model.Category;
import com.example.jpaexercise.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCoffee(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category Successfully added!");
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity updateCoffee(@PathVariable Integer id,@Valid @RequestBody Category category, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = categoryService.UpdateCategory(id,category);
        if(isUpdated){
            return ResponseEntity.status(200).body("Category Successfully updated!");
        }
        return ResponseEntity.status(400).body("Category not found!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoffee(@PathVariable Integer id){
        boolean isDeleted = categoryService.DeleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Category Successfully deleted!");
        }
        return ResponseEntity.status(400).body("Category not found!");
    }

}
