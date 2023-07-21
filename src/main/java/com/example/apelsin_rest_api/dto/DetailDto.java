package com.example.apelsin_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDto {
    private Integer ordId;
    private Integer prId;
    private short quantity;

}
