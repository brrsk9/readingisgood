package com.readingisgood.repository;

import com.readingisgood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.customerId = ?1")
    public List<Order> getOrdersOfCustomer(Long customerId);

}
