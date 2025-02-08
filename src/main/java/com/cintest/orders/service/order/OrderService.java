package com.cintest.orders.service.order;

import java.util.List;

import com.cintest.orders.dto.order.OrderMapper;
import com.cintest.orders.dto.order.OrderResponse;
import com.cintest.orders.dto.product.ProductMapper;
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
    private OrderMapper orderMapper;

    public List<OrderResponse> getAllOrders() {
        return orderMapper.toResponseList(orderRepository.findAll());
    }

    public OrderResponse getOrderById(int orderId) {
        return orderMapper.toResponse(this.findOrderById(orderId));
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        this.setProduct(order, orderRequest.getProductId());
        order.setTotalPrice(this.getTotalPrice(order));
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(int orderId, OrderRequest orderRequest) {
        Order order = this.findOrderById(orderId);
        this.validate(order, orderRequest);
        this.setProduct(order, orderRequest.getProductId());
        orderMapper.updateModel(orderRequest, order);
        order.setTotalPrice(this.getTotalPrice(order));
        return orderMapper.toResponse(orderRepository.save(order));
    }

    private void validate(Order order, OrderRequest orderRequest) {
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

    private int getTotalPrice(Order order) {
        return order.getProduct().getPrice() * (order.getUnits() + order.getBonus() + order.getPromo());
    }

    private void setProduct(Order order, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        order.setProduct(product);
    }

    private Order findOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
    }
}
