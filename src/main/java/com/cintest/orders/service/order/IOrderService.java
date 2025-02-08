package com.cintest.orders.service.order;

import com.cintest.orders.dto.order.OrderRequest;
import com.cintest.orders.dto.order.OrderResponse;
import com.cintest.orders.model.Order;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(int orderId);

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(int orderId, OrderRequest orderRequest);
}
