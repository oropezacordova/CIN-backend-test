package com.cintest.orders.dto.product;

import com.cintest.orders.model.Product;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int price;
}
