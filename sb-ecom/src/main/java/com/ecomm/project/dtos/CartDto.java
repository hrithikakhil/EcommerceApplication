package com.ecomm.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long cartId;
    private Double totalPrice = 0.00;
    private List<ProductDTO> products = new ArrayList<>();

}
