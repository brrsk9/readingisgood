package com.readingisgood.service;

import com.readingisgood.model.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findById(Long id);
}
