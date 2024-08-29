package com.ecomm.project.controller;

import com.ecomm.project.dtos.ProductDTO;
import com.ecomm.project.dtos.ProductResponse;
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
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDto, @PathVariable Long categoryId) {
        ProductDTO savedProductDto = productService.addProduct(productDto, categoryId);
        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
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
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDto, @PathVariable Long productId) {
        ProductDTO savedProductDto = productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(savedProductDto, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        ProductDTO productDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
