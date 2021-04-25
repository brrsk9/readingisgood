package com.readingisgood.service;

import com.readingisgood.model.Order;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> getOrderListOfCustomer(Long customerId);

    Order findById(Long orderId);
}
