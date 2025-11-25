package dev.ebecast.product_service.kafka.producer;

import dev.ebecast.product_service.kafka.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventProducer {

    private static final Logger log = LoggerFactory.getLogger(ProductEventProducer.class);
    private static final String TOPIC = "ecommerce.products.created";

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductEventProducer(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishProductCreated(ProductCreatedEvent event) {
        System.out.println("🔍 KAFKA DEBUG - Método publishProductCreated llamado");
        System.out.println("🔍 KAFKA DEBUG - Event: " + event);

        String key = event.getProductId().toString();

        log.info("Publishing ProductCreatedEvent: productId={}, name={}",
                event.getProductId(), event.getName());

        try {
            System.out.println("🔍 KAFKA DEBUG - Antes de kafkaTemplate.send");

            kafkaTemplate.send(TOPIC, key, event)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            System.out.println("❌ KAFKA DEBUG - ERROR: " + ex.getMessage());
                            log.error("Failed to publish ProductCreatedEvent: productId={}",
                                    event.getProductId(), ex);
                        } else {
                            System.out.println("✅ KAFKA DEBUG - ÉXITO - Partition: " +
                                    result.getRecordMetadata().partition() + ", Offset: " +
                                    result.getRecordMetadata().offset());
                            log.info("ProductCreatedEvent published successfully: productId={}, partition={}",
                                    event.getProductId(),
                                    result.getRecordMetadata().partition());
                        }
                    });

            System.out.println("🔍 KAFKA DEBUG - Después de kafkaTemplate.send");

        } catch (Exception e) {
            System.out.println("💥 KAFKA DEBUG - EXCEPCIÓN: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

