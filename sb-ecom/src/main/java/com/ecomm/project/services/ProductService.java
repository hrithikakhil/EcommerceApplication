package com.ecomm.project.services;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.models.Product;

public interface ProductService {

     ProductDTO addProduct(Product product, Long CategoryId);
}
