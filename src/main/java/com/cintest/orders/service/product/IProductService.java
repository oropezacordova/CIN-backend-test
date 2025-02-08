package com.cintest.orders.service.product;

import java.util.List;

import com.cintest.orders.dto.product.ProductRequest;
import com.cintest.orders.dto.product.ProductResponse;
import com.cintest.orders.model.Product;

public interface IProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}