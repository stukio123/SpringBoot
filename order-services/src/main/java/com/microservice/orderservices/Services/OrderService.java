package com.microservice.orderservices.Services;

import com.microservice.orderservices.Entities.Order;

public interface OrderService {
    void createOrder(Order order);
}
