package com.example.apelsin_rest_api.service;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.entity.Customer;
import com.example.apelsin_rest_api.repositary.CustomerRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepositary customerRepositary;
    public ApiResponse add(Customer customer) {
        customerRepositary.save(customer);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, Customer customer) {

        Optional<Customer> byId = customerRepositary.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Customer edited = byId.get();
        edited.setName(customer.getName());
        edited.setAddress(customer.getAddress());
        edited.setCountry(customer.getCountry());
        edited.setPhone(customer.getPhone());
        customerRepositary.save(edited);
        return new ApiResponse("Edited", true);
    }
}
