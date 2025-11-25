package dev.ebecast.product_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.ebecast.product_service.dto.ProductRequest;
import dev.ebecast.product_service.dto.ProductResponse;
import dev.ebecast.product_service.dto.ProductSummaryResponse;
import dev.ebecast.product_service.exception.ResourceNotFoundException;
import dev.ebecast.product_service.kafka.event.ProductCreatedEvent;
import dev.ebecast.product_service.kafka.producer.ProductEventProducer;
import dev.ebecast.product_service.mapper.ProductMapper;
import dev.ebecast.product_service.model.Category;
import dev.ebecast.product_service.model.Product;
import dev.ebecast.product_service.repository.CategoryRepository;
import dev.ebecast.product_service.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductEventProducer productEventProducer;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ProductEventProducer productEventProducer) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productEventProducer = productEventProducer;
    }

    public List<ProductResponse> findAll(String name) {
        List<Product> products = (name == null || name.isBlank())
                ? productRepository.findAll()
                : productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto " + id + " no encontrado"));
        return ProductMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        System.out.println("🎯 INICIO CREATE");

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        System.out.println("✅ Categoría cargada: " + category.getId());

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);

        System.out.println("🔍 Antes de guardar - Category: " +
                (product.getCategory() != null ? product.getCategory().getId() : "NULL"));

        Product saved = productRepository.save(product);

        System.out.println("🔍 Después de guardar - Category: " +
                (saved.getCategory() != null ? saved.getCategory().getId() : "NULL"));

        System.out.println("🔍 ANTES del mapper");
        ProductResponse response = ProductMapper.toResponse(saved);
        System.out.println("🔍 DESPUÉS del mapper");

        ProductCreatedEvent event = new ProductCreatedEvent(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getCategory().getId()
        );
        productEventProducer.publishProductCreated(event);


        return response;
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        System.out.println("=== DEBUG UPDATE INICIADO ===");
        System.out.println("📥 Update categoryId: " + request.categoryId());

        // 1. Buscar el producto existente
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto " + id + " no encontrado"));

        // 2. Buscar la categoría
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría " + request.categoryId() + " no encontrada"));

        System.out.println("✅ Categoría encontrada para update: " + category.getId());

        // 3. Actualizar el producto DIRECTAMENTE
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(category);

        System.out.println("✅ Producto actualizado - Category: " + product.getCategory().getId());

        // 4. Guardar
        Product updated = productRepository.save(product);
        System.out.println("=== DEBUG UPDATE FINALIZADO ===\n");

        return ProductMapper.toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto " + id + " no encontrado"));
        productRepository.delete(product);
    }

    /*public List<ProductSummaryResponse> findAllAndSummary() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toSummaryResponse)
                .collect(Collectors.toList());
    }*/
}