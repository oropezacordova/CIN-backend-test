package com.cintest.orders.dto.product;

import com.cintest.orders.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    Product toEntity(ProductRequest productRequest);

    void updateModel(ProductRequest productRequest, @MappingTarget Product product);
}
