package com.specmatic.controller;

import com.specmatic.entity.ErrorResponseBody;
import com.specmatic.entity.Product;
import com.specmatic.servcie.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String type) {
        List<Product> products = productService.getProductsByType(type);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        ErrorResponseBody errorResponse = ErrorResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("No products found for the specified type.")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product productDetails) {
              productService.createProduct(productDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
