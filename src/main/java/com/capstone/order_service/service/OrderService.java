package com.capstone.order_service.service;

import com.capstone.order_service.client.InventoryClient;
import com.capstone.order_service.dto.OrderRequest;
import com.capstone.order_service.modal.Order;
import com.capstone.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;

    public boolean placeOrder(OrderRequest orderRequest) {

        boolean isStockPresent = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isStockPresent) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
            return true;
        }
        else{
            //throw new IllegalStateException("Stock not available");
            return false;
        }

    }
}
