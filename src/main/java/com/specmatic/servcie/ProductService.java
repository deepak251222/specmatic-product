package com.specmatic.servcie;
import com.specmatic.entity.Product;
import com.specmatic.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();
    public void  createProduct(Product productDetails) {
        Integer size = products.size();
           Product  build = Product.builder()
                .id(size++)
                .name(productDetails.getName())
                .type(productDetails.getType())
                .inventory(productDetails.getInventory())
                .build();
               products.add(build);

    }


    public List<Product> getProductsByType(String type) {
        return products.stream()
                .filter(product -> type == null || product.getType().name().equalsIgnoreCase(type))
                .map(product -> Product.builder()
                        .name(product.getName())
                        .type(product.getType())
                        .inventory(product.getInventory())
                        .build())
                .collect(Collectors.toList());
    }



}
