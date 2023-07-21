package com.example.apelsin_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private Timestamp time;
    private double ammount;
    private Integer invId;
}
