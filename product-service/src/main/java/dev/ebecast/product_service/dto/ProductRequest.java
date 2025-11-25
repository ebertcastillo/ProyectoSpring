package dev.ebecast.product_service.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotBlank(message = "{product.name.notblank}")
        @Size(max = 120)
        String name,

        @Size(max = 255)
        String description,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false, message = "{product.price.min}")
        BigDecimal price,

        @NotNull
        @PositiveOrZero(message = "{product.stock.min}")
        Integer stock,

        @NotNull
        Long categoryId
) { }