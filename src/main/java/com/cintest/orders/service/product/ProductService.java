package com.cintest.orders.service.product;

import java.util.List;

import com.cintest.orders.dto.product.ProductMapper;
import com.cintest.orders.dto.product.ProductResponse;
import org.springframework.stereotype.Service;

import com.cintest.orders.dto.product.ProductRequest;
import com.cintest.orders.model.Product;
import com.cintest.orders.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productMapper.toResponseList(productRepository.findAll());
    }
}
