package com.example.apelsin_rest_api.service;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.dto.OrderDto;
import com.example.apelsin_rest_api.entity.Customer;
import com.example.apelsin_rest_api.entity.Order;
import com.example.apelsin_rest_api.repositary.CustomerRepositary;
import com.example.apelsin_rest_api.repositary.OrderRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepositary orderRepositary;
    final CustomerRepositary customerRepository;
    public ApiResponse add(OrderDto dto) {
        Order order = new Order();
        Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustId());
        if (optionalCustomer.isEmpty()) return new ApiResponse("Not found!", false);
        order.setCustomer(optionalCustomer.get());
        order.setDate(order.getDate());
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, OrderDto dto) {

        Optional<Order> byId = orderRepositary.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Order order = byId.get();

        order.setDate(dto.getDate());
        Optional<Customer> optionalGM = customerRepository.findById(dto.getCustId());
        order.setCustomer(optionalGM.get());
        orderRepositary.save(order);
        return new ApiResponse("Edited", true);
    }
}
