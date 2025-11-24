package dev.ebecast.product_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.ebecast.product_service.dto.CategoryRequest;
import dev.ebecast.product_service.dto.CategoryResponse;
import dev.ebecast.product_service.dto.ProductResponse;
import dev.ebecast.product_service.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Obtener todos los productos de una categoría específica
    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductResponse>> findProducts(@PathVariable Long id) {
        List<ProductResponse> products = service.findProducts(id);
        return ResponseEntity.ok(products);
    }
}
