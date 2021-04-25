package com.readingisgood.dto;

import com.readingisgood.enums.Status;
import com.readingisgood.model.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class CustomerDTO {

    private String name;
    private String surname;
    private String address;

    public Customer getCustomerFromDTO(User user) {
        Customer customer = new Customer();
        customer.setName(this.name);
        customer.setSurname(this.surname);
        customer.setAddress(this.address);
        customer.setCreationDate(new Date());
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedUser(user.getUsername());

        return customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
