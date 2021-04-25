package com.readingisgood.service.impl;

import com.google.common.base.Preconditions;
import com.readingisgood.model.Book;
import com.readingisgood.model.Customer;
import com.readingisgood.model.Order;
import com.readingisgood.repository.OrderRepository;
import com.readingisgood.service.BookService;
import com.readingisgood.service.CustomerService;
import com.readingisgood.service.OrderService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;

    @Override
    public Order save(Order order) {
        Customer customer = customerService.findById(order.getCustomerId());
        Preconditions.checkNotNull(customer, "Customer not found.");

        Book book = bookService.findById(order.getBookId());
        Preconditions.checkNotNull(book, "Book not found.");
        Preconditions.checkArgument(book.getStock().compareTo(0L) > 0, "Out of stock.");

        book.setStock(book.getStock() - 1);
        bookService.save(book);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> getOrderListOfCustomer(Long customerId) {
        List<Order> ordersOfCustomer = orderRepository.getOrdersOfCustomer(customerId);
        for (Order order : ordersOfCustomer) {
            Hibernate.initialize(order.getBook());
            Hibernate.initialize(order.getCustomer());
        }
        return ordersOfCustomer;
    }

    @Override
    @Transactional
    public Order findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            Hibernate.initialize(order.getBook());
            Hibernate.initialize(order.getCustomer());
        }
        return order;
    }

}
