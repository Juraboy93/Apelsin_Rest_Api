package com.example.apelsin_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne
    private Customer customer;

    private boolean active=true;
}
