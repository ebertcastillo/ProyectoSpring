package dev.ebecast.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.ebecast.product_service.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
}