package dev.ebecast.product_service.mapper;

import dev.ebecast.product_service.dto.ProductRequest;
import dev.ebecast.product_service.dto.ProductResponse;
import dev.ebecast.product_service.dto.ProductSummaryResponse;
import dev.ebecast.product_service.model.Category;
import dev.ebecast.product_service.model.Product;

public final class ProductMapper {

    private ProductMapper() {
        throw new AssertionError("Utility class, no debe instanciarse");
    }

    public static ProductResponse toResponse(Product product) {
        System.out.println("🔍 MAPPER - Product ID: " + product.getId());
        System.out.println("🔍 MAPPER - Product Category: " +
                (product.getCategory() != null ? product.getCategory().getId() : "NULL"));

        Category category = product.getCategory();

        // Si category es null, intentar cargarla
        if (category == null && product.getId() != null) {
            System.out.println("⚠️  Categoría es NULL, hay problema de carga Lazy");
        }

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


    /*public static ProductSummaryResponse toSummaryResponse(Product product) {
        return new ProductSummaryResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.
        );
    }*/

    public static Product toEntity(ProductRequest request, Product entity, Category category) {
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setStock(request.stock());
        entity.setCategory(category);
        return entity;
    }
}