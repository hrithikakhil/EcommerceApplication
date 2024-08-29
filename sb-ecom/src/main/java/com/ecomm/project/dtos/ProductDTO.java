package com.ecomm.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private String image;
    private String Description;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double specialPrice;
}
