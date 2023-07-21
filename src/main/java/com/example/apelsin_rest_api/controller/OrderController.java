package com.example.apelsin_rest_api.controller;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.dto.OrderDto;
import com.example.apelsin_rest_api.entity.Order;
import com.example.apelsin_rest_api.repositary.CustomerRepositary;
import com.example.apelsin_rest_api.repositary.OrderRepositary;
import com.example.apelsin_rest_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor

public class OrderController {

        final OrderRepositary orderRepositary;
        final CustomerRepositary customerRepositary;
        final OrderService orderService;
        @GetMapping
        public HttpEntity<?> getAll() {
            List<Order> all = orderRepositary.findAllByActiveTrue();
            return ResponseEntity.ok().body(all);
        }
        @GetMapping("/{id}")
        public HttpEntity<?> getOne(@PathVariable Integer id) {
            Optional<Order> byId = orderRepositary.findById(id);
            return ResponseEntity.status(byId.isEmpty() ?
                    HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new Order()));
        }
        @PostMapping
        public HttpEntity<?> add(@RequestBody OrderDto dto) {
            ApiResponse response = orderService.add(dto);
            return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
        }

        @PutMapping("/{id}")
        public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
            ApiResponse response = orderService.edit(id, orderDto);
            return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
        }

        @DeleteMapping("/{id}")
        public HttpEntity<?> delete(@PathVariable Integer id) {
            Optional<Order> byId = orderRepositary.findById(id);
            if (byId.isEmpty()) return ResponseEntity.status(404).body("NOT FOUND");
            Order order = byId.get();
            order.setActive(false);
            orderRepositary.save(order);
            return ResponseEntity.ok().body("DELETED!");
        }
        @GetMapping("/byCustId/{id}")
        public HttpEntity<?> getAllByCust(@PathVariable Integer id) {
            return ResponseEntity.ok().body(orderRepositary.findAllByCustIdActive(id));
        }
        @GetMapping("/orders_without_details")
        public HttpEntity<?> getOrders_without_details() {
            List<Order> all = orderRepositary.getOrders_without_details();
            return ResponseEntity.ok().body(all);
        }
        @GetMapping("/orders_without_invoices")
        public HttpEntity<?> getOrders_without_invoices() {
            List<Order> all = orderRepositary.getOrders_without_invoices();
            return ResponseEntity.ok().body(all);
        }



    }

