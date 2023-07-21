package com.example.apelsin_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto {
    private Integer ordId;
    private double ammount;
    private Date issued;
    private Date due;
}
