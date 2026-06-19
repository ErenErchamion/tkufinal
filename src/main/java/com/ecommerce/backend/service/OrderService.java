package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CreateOrderRequest;
import com.ecommerce.backend.entity.Order;
import com.ecommerce.backend.entity.OrderItem;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final Random random = new Random();

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request, String userId, String userEmail) {
        log.info("Creating order for User: {} (Email: {})", userId, userEmail);

        String orderId = generateUniqueOrderNumber();

        Order order = new Order(orderId, LocalDateTime.now(), request.getTotalAmount(), userId, userEmail, new ArrayList<>());

        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + itemRequest.getProductId()));

            if (product.getStockCount() < itemRequest.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for product: " + product.getName() + 
                                                ". Available: " + product.getStockCount() + ", Requested: " + itemRequest.getQuantity());
            }
            product.setStockCount(product.getStockCount() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(null, order, itemRequest.getProductId(), itemRequest.getUnitPrice(), itemRequest.getQuantity(), itemRequest.getLineTotal());

            order.getItems().add(orderItem);
        }

        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully. Order Number: {}", savedOrder.getId());
        return savedOrder;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    private String generateUniqueOrderNumber() {
        String orderNumber;
        do {
            orderNumber = String.valueOf(100000 + random.nextInt(900000));
        } while (orderRepository.existsById(orderNumber));
        return orderNumber;
    }
}
