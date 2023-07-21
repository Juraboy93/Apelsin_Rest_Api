package com.example.apelsin_rest_api.repositary;

import com.example.apelsin_rest_api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepositary extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByActiveTrue();
}
