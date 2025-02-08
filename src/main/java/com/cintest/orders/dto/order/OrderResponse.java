package com.cintest.orders.dto.order;

import com.cintest.orders.dto.product.ProductResponse;
import lombok.Data;

@Data
public class OrderResponse {
    private int id;
    private int units;
    private int bonus;
    private int promo;
    private int totalPrice;
    private ProductResponse product;
}
