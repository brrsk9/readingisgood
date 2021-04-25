package com.readingisgood.dto;

import com.readingisgood.enums.OrderStatus;
import com.readingisgood.enums.Status;
import com.readingisgood.model.Order;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class OrderDTO {

    private Long bookId;
    private Long customerId;

    public Order getOrderFromDTO(User user) {
        Order order = new Order();
        order.setBookId(this.bookId);
        order.setCustomerId(this.customerId);
        order.setOrderStatus(OrderStatus.RECIEVED);
        order.setCreationDate(new Date());
        order.setStatus(Status.ACTIVE);
        order.setCreatedUser(user.getUsername());

        return order;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
