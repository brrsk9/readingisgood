package com.readingisgood.controller;

import com.google.common.base.Preconditions;
import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.enums.ResponseStatus;
import com.readingisgood.model.Customer;
import com.readingisgood.response.ResponseModel;
import com.readingisgood.service.CustomerService;
import com.readingisgood.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customer Controller")
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel<Customer>> createCustomer(@RequestBody CustomerDTO customerDTO, @AuthenticationPrincipal User user) {
        ResponseEntity<ResponseModel<Customer>> response = null;
        try {
            validateCreateCustomerInputs(customerDTO);

            Customer customer = customerDTO.getCustomerFromDTO(user);
            Customer savedCustomer = customerService.save(customer);
            ResponseModel<Customer> successResponse = new ResponseModel<>(savedCustomer, ResponseStatus.SUCCESS, Constants.SAVED);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<Customer> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    private void validateCreateCustomerInputs(CustomerDTO customerDTO) {
        Preconditions.checkNotNull(customerDTO.getName(), "Name can not be null");
        Preconditions.checkNotNull(customerDTO.getSurname(), "Surname can not be null");
        Preconditions.checkNotNull(customerDTO.getAddress(), "Address can not be null");
    }
}
