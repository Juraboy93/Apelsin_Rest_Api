package com.example.apelsin_rest_api.service;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.dto.ProductDto;
import com.example.apelsin_rest_api.entity.Category;
import com.example.apelsin_rest_api.entity.Product;
import com.example.apelsin_rest_api.repositary.CategoryRepositary;
import com.example.apelsin_rest_api.repositary.ProductRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    ProductRepositary productRepositary;
    @Autowired
    CategoryRepositary categoryRepositary;

    public ApiResponse add(ProductDto dto) {
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Category save = categoryRepositary.save(category);
        Product product = new Product();
        product.setCategory(save);
//        product.setDescription(dto.getDescription());
//        product.setPrice(dto.getPrice());
//        product.setFoto(dto.getPhoto());
        product.setName(dto.getName());
        productRepositary.save(product);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, ProductDto dto) {

        Optional<Product> byId = productRepositary.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!",
                false);
        Product product = byId.get();
        product.setName(dto.getName());
//        product.setPrice(dto.getPrice());
//        product.setFoto(dto.getPhoto());
//        product.setDescription(dto.getDescription());
        Category category = product.getCategory();
        product.setCategory(category);
        productRepositary.save(product);
        return new ApiResponse("Edited", true);
    }
}
