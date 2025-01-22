package com.cintest.orders.service.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cintest.orders.dto.order.OrderRequest;
import com.cintest.orders.model.Order;
import com.cintest.orders.model.Product;
import com.cintest.orders.repository.OrderRepository;
import com.cintest.orders.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = orderRequest.toEntity();
        this.setProduct(order, orderRequest.getProductId());
        order.setTotalPrice(this.getTotalPrice(order));
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(int orderId, OrderRequest orderRequest) {
        Order order = this.getOrderById(orderId);
        this.validate(order, orderRequest);
        if (orderRequest.getProductId() != order.getProduct().getId()) {
            this.setProduct(order, orderRequest.getProductId());
        }
        order.setUnits(orderRequest.getUnits());
        order.setBonus(orderRequest.getBonus());
        order.setPromo(orderRequest.getPromo());
        order.setTotalPrice(this.getTotalPrice(order));
        return orderRepository.save(order);
    }

    public void validate(Order order, OrderRequest orderRequest) {
        if (orderRequest.getUnits() > order.getUnits()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't increase the number of units");
        }
        if (orderRequest.getBonus() > order.getBonus()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't increase the bonus");
        }
        if (orderRequest.getPromo() > order.getPromo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't increase the promo");
        }
    }

    public int getTotalPrice(Order order) {
        return order.getProduct().getPrice() * (order.getUnits() + order.getBonus() + order.getPromo());
    }

    public void setProduct(Order order, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        order.setProduct(product);
    }
}
