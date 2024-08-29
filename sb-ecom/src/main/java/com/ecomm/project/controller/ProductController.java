package com.ecomm.project.controller;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.dtos.ProductResponse;
import com.ecomm.project.models.Product;
import com.ecomm.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts() {
        List<ProductDTO> allProducts = productService.getAllProducts();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(allProducts);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> allProductsByCategory = productService.searchByCategory(categoryId);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(allProductsByCategory);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword) {
        List<ProductDTO> allProductsByCategory = productService.searchByKeyword(keyword);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(allProductsByCategory);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
