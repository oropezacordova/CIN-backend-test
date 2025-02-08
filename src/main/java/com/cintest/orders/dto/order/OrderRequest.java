package com.cintest.orders.dto.order;

import com.cintest.orders.model.Order;

import lombok.Data;

@Data
public class OrderRequest {
    private int units;
    private int bonus;
    private int promo;
    private int productId;
}
