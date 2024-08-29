package com.ecomm.project.services;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.models.Product;

import java.util.List;

public interface ProductService {

     ProductDTO addProduct(Product product, Long CategoryId);

     List<ProductDTO> getAllProducts();

     List<ProductDTO> searchByCategory(Long categoryId);

     List<ProductDTO> searchByKeyword(String keyword);
}
