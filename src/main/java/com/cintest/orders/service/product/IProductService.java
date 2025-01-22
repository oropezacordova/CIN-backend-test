package com.cintest.orders.service.product;

import java.util.List;

import com.cintest.orders.dto.product.ProductRequest;
import com.cintest.orders.model.Product;

public interface IProductService {
    Product createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();
}