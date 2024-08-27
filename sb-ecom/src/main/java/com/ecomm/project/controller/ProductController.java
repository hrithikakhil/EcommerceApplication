package com.ecomm.project.controller;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.models.Product;
import com.ecomm.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        ProductDTO productDto = productService.addProduct(product, categoryId);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }
}
