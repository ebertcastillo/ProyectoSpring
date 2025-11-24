package dev.ebecast.product_service.mapper;

import dev.ebecast.product_service.dto.ProductRequest;
import dev.ebecast.product_service.dto.ProductResponse;
import dev.ebecast.product_service.model.Category;
import dev.ebecast.product_service.model.Product;

public final class ProductMapper {

    private ProductMapper() { throw new AssertionError(); }

    public static ProductResponse toResponse(Product product) {
        Category category = product.getCategory();
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                category != null ? category.getId() : null,
                category != null ? category.getName() : null
        );
    }

    public static Product toEntity(ProductRequest request, Product entity, Category category) {
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setStock(request.stock());
        entity.setCategory(category);
        return entity;
    }
}