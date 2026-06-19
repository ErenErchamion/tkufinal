package com.ecommerce.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class CreateOrderRequest {
    @NotNull(message = "Total amount cannot be null")
    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemRequest> items;

    public CreateOrderRequest() {}

    public CreateOrderRequest(BigDecimal totalAmount, List<OrderItemRequest> items) {
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public static class OrderItemRequest {
        @NotNull(message = "Product ID cannot be null")
        private Long productId;

        @NotNull(message = "Unit price cannot be null")
        @Positive(message = "Unit price must be positive")
        private BigDecimal unitPrice;

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be positive")
        private Integer quantity;

        @NotNull(message = "Line total cannot be null")
        @Positive(message = "Line total must be positive")
        private BigDecimal lineTotal;

        public OrderItemRequest() {}

        public OrderItemRequest(Long productId, BigDecimal unitPrice, Integer quantity, BigDecimal lineTotal) {
            this.productId = productId;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.lineTotal = lineTotal;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getLineTotal() {
            return lineTotal;
        }

        public void setLineTotal(BigDecimal lineTotal) {
            this.lineTotal = lineTotal;
        }
    }
}
