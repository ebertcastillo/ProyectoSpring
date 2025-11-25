package dev.ebecast.product_service.controller;

import dev.ebecast.product_service.model.Category;
import dev.ebecast.product_service.model.Product;
import dev.ebecast.product_service.repository.CategoryRepository;
import dev.ebecast.product_service.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DebugController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        System.out.println("=== CATEGORÍAS EN BD ===");
        categories.forEach(cat ->
                System.out.println("Categoría ID: " + cat.getId() + ", Nombre: " + cat.getName())
        );
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/test-fixed")
    public ResponseEntity<?> testFixedProduct() {
        try {
            System.out.println("=== TEST MANUAL ===");

            // 1. Verificar categorías
            List<Category> categories = categoryRepository.findAll();
            if (categories.isEmpty()) {
                // Crear una categoría si no existe
                Category category = new Category();
                category.setName("Electrónicos");
                category.setDescription("Productos electrónicos");
                Category savedCategory = categoryRepository.save(category);
                System.out.println("✅ Categoría creada: " + savedCategory.getId());
            }

            categories = categoryRepository.findAll();
            Long categoryId = categories.get(0).getId();
            System.out.println("✅ Usando categoría ID: " + categoryId);

            // 2. Crear producto MANUALMENTE
            Product product = new Product();
            product.setName("Producto Test DEBUG");
            product.setDescription("Descripción test DEBUG");
            product.setPrice(new BigDecimal("199.99"));
            product.setStock(5);

            // ASIGNAR CATEGORÍA MANUALMENTE
            Category category = categoryRepository.findById(categoryId).get();
            product.setCategory(category);

            System.out.println("✅ Producto category antes de save: " +
                    (product.getCategory() != null ? product.getCategory().getId() : "NULL"));

            // 3. Guardar
            Product saved = productRepository.save(product);
            System.out.println("✅ Producto guardado ID: " + saved.getId());

            return ResponseEntity.ok("Producto creado: " + saved.getId());

        } catch (Exception e) {
            System.out.println("❌ Error en test: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}