package com.example.apelsin_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private  String name;
    private Integer categoryId;
//    private String description;
//    private double price;
//    private String photo;
}
