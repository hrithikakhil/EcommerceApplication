package com.ecomm.project.services;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.dtos.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

     ProductDTO addProduct(ProductDTO productDTO, Long CategoryId);

     ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

     ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

     ProductResponse searchByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

     ProductDTO updateProduct(ProductDTO productDTO, Long productId);

     ProductDTO deleteProduct(Long productId);

     ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
