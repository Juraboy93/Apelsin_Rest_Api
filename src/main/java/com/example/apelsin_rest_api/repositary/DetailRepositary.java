package com.example.apelsin_rest_api.repositary;

import com.example.apelsin_rest_api.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepositary extends JpaRepository<Detail, Integer> {
        List<Detail> findAllByActiveTrue();
        List<Detail> findAllByActiveTrueAndProductId(Integer id);
        List<Detail> findAllByActiveTrueAndOrdId(Integer id);
}
