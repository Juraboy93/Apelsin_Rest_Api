package com.example.apelsin_rest_api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private int quantity;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    private boolean active=true;
}
