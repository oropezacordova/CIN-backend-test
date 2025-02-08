package com.cintest.orders.dto.order;

import com.cintest.orders.model.Order;
import com.cintest.orders.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = Product.class
)
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> orders);

    Order toEntity(OrderRequest orderRequest);

    void updateModel(OrderRequest orderRequest, @MappingTarget Order order);
}
