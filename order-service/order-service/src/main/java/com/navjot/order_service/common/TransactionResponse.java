package com.navjot.order_service.common;


import com.navjot.order_service.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;
}
