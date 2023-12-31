package com.example.apelsin_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column(nullable = false)
    private String name;
@Column(nullable = false)
    private String country;

    private String address;

    private String phone;
    private boolean active=true;
}
