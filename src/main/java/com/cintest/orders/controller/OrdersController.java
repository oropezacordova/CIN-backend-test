package com.cintest.orders.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cintest.orders.dto.order.OrderRequest;
import com.cintest.orders.model.Order;
import com.cintest.orders.service.order.IOrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
@CrossOrigin("*")
public class OrdersController {

    private IOrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllProducts() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getProductById(@PathVariable int orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderRequest));
    }
}
