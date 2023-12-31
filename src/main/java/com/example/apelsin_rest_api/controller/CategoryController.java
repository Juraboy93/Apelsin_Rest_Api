package com.example.apelsin_rest_api.controller;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.entity.Category;
import com.example.apelsin_rest_api.repositary.CategoryRepositary;
import com.example.apelsin_rest_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
class CategoryController {
    @Autowired
    CategoryService categoryService;
   @Autowired
    CategoryRepositary categoryRepository;

               @GetMapping
        public HttpEntity<?> getAll() {
            List<Category> all = categoryRepository.findAllByActiveTrue();
            return ResponseEntity.ok().body(all);
        }

        @GetMapping("/{id}")
        public HttpEntity<?> getOne(@PathVariable Integer id) {
            Optional<Category> byId = categoryRepository.findById(id);
            return ResponseEntity.status(byId.isEmpty() ?
                    HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse
                    (new Category()));
        }
        @PostMapping
        public HttpEntity<?> add(@RequestBody Category category) {
            ApiResponse response = categoryService.add(category);
            return ResponseEntity.status(response.isSuccess() ? 201 : 409).
                    body(response);
        }

        @PutMapping("/{id}")
        public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody
                Category category) {
            ApiResponse response = categoryService.edit(id, category);
            return ResponseEntity.status(response.isSuccess() ? 200 : 409)
                    .body(response);
        }

        @DeleteMapping("/{id}")
        public HttpEntity<?> delete(@PathVariable Integer id) {
            Optional<Category> byId = categoryRepository.findById(id);
            if (byId.isEmpty()) return ResponseEntity.status(404).
                    body("NOT FOUND");
            Category category = byId.get();
            category.setActive(false);
            categoryRepository.save(category);
            return ResponseEntity.ok().body("DELETED!");
        }

        @GetMapping("/change/{id}")
        public HttpEntity<?> changeActive(@PathVariable Integer id, @RequestParam boolean status) {
            Optional<Category> optionalCategory = categoryRepository.findById(id);
            Category category = optionalCategory.get();
            category.setActive(!category.isActive());
            categoryRepository.save(category);
            return ResponseEntity.ok().body(optionalCategory.orElseThrow(RuntimeException::new));
        }


    }

