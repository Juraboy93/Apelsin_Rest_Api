package com.example.apelsin_rest_api.controller;


import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.dto.ProductDto;
import com.example.apelsin_rest_api.entity.Product;
import com.example.apelsin_rest_api.repositary.CategoryRepositary;
import com.example.apelsin_rest_api.repositary.ProductRepositary;
import com.example.apelsin_rest_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

        final ProductRepositary productRepositary;
        final CategoryRepositary categoryRepositary;
        final ProductService productService;
        @GetMapping
        public HttpEntity<?> getAll() {
            List<Product> all = productRepositary.findAllByActiveTrue();
            return ResponseEntity.ok().body(all);
        }
        @GetMapping("/{id}")
        public HttpEntity<?> getOne(@PathVariable Integer id) {
            Optional<Product> byId = productRepositary.findById(id);
            return ResponseEntity.status(byId.isEmpty() ?
                    HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse
                    (new Product()));
        }
        @PostMapping
        public HttpEntity<?> add(@RequestBody ProductDto dto) {
            ApiResponse response = productService.add(dto);
            return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
        }

        @PutMapping("/{id}")
        public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
            ApiResponse response = productService.edit(id, productDto);
            return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
        }

        @DeleteMapping("/{id}")
        public HttpEntity<?> delete(@PathVariable Integer id) {
            Optional<Product> byId = productRepositary.findById(id);
            if (byId.isEmpty()) return ResponseEntity.status(404).body("NOT FOUND");
            Product product = byId.get();
            product.setActive(false);
            productRepositary.save(product);
            return ResponseEntity.ok().body("DELETED!");
        }

        @GetMapping("/high_demand_products")
        public HttpEntity<?> getHigh_demand_products() {
            List<Product> all = productRepositary.getHigh_demand_products();
            return ResponseEntity.ok().body(all);
        }

        @GetMapping("/bulk_products")
        public HttpEntity<?> getBulk_products() {
            List<Product> all = productRepositary.getBulk_products();
            return ResponseEntity.ok().body(all);
        }
    }


