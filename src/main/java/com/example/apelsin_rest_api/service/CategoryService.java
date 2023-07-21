package com.example.apelsin_rest_api.service;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.entity.Category;
import com.example.apelsin_rest_api.repositary.CategoryRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepositary categoryRepositary;
    public ApiResponse add(Category category) {
        categoryRepositary.save(category);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, Category category) {

        Optional<Category> byId = categoryRepositary.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Category edited = byId.get();
        edited.setName(category.getName());
        categoryRepositary.save(edited);
        return new ApiResponse("Edited", true);
    }
}
