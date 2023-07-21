package com.example.apelsin_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
   @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date due;
    @OneToOne
    private Order order;

    private boolean active=true;
}
