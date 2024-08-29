package com.ecomm.project.services;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

     ProductDTO addProduct(ProductDTO productDTO, Long CategoryId);

     List<ProductDTO> getAllProducts();

     List<ProductDTO> searchByCategory(Long categoryId);

     List<ProductDTO> searchByKeyword(String keyword);

     ProductDTO updateProduct(ProductDTO productDTO, Long productId);

     ProductDTO deleteProduct(Long productId);

     ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
