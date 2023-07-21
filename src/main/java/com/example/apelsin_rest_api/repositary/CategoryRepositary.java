package com.example.apelsin_rest_api.repositary;

import com.example.apelsin_rest_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface CategoryRepositary extends JpaRepository<Category, Integer> {
    List<Category> findAllByActiveTrue();
}
