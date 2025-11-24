package dev.ebecast.product_service.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description
) {}