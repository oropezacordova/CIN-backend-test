package com.cintest.orders.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cintest.orders.dto.product.ProductRequest;
import com.cintest.orders.model.Product;
import com.cintest.orders.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
