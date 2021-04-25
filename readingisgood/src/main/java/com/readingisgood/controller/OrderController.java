package com.readingisgood.controller;

import com.google.common.base.Preconditions;
import com.readingisgood.dto.OrderDTO;
import com.readingisgood.enums.ResponseStatus;
import com.readingisgood.model.Order;
import com.readingisgood.response.ResponseModel;
import com.readingisgood.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Controller")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel<Order>> createOrder(@RequestBody OrderDTO orderDTO, @AuthenticationPrincipal User user) {
        ResponseEntity<ResponseModel<Order>> response = null;
        try {
            validateCreateOrderInputs(orderDTO);

            Order order = orderDTO.getOrderFromDTO(user);
            Order savedOrder = orderService.save(order);
            ResponseModel<Order> successResponse = new ResponseModel<>(savedOrder, ResponseStatus.SUCCESS, StringUtils.EMPTY);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<Order> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    @GetMapping(value = "/listByCustomerId")
    public ResponseEntity<ResponseModel<List<Order>>> getOrderListOfCustomer(@RequestParam Long customerId) {
        ResponseEntity<ResponseModel<List<Order>>> response = null;
        try {
            Preconditions.checkNotNull(customerId, "Customer can not be null");

            List<Order> orderList = orderService.getOrderListOfCustomer(customerId);
            ResponseModel<List<Order>> successResponse = new ResponseModel<>(orderList, ResponseStatus.SUCCESS, StringUtils.EMPTY);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<List<Order>> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<ResponseModel<Order>> getOrderById(@RequestParam Long orderId) {
        ResponseEntity<ResponseModel<Order>> response = null;
        try {
            Preconditions.checkNotNull(orderId, "Customer can not be null");

            Order order = orderService.findById(orderId);
            ResponseModel<Order> successResponse = new ResponseModel<>(order, ResponseStatus.SUCCESS, StringUtils.EMPTY);
            response = ResponseEntity.ok(successResponse);
        }
        catch (Exception e) {
            ResponseModel<Order> failureResponse = new ResponseModel<>(null, ResponseStatus.FAILURE, e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureResponse);
        }
        return response;
    }

    private void validateCreateOrderInputs(OrderDTO orderDTO) {
        Preconditions.checkNotNull(orderDTO.getBookId(), "Book can not be null");
        Preconditions.checkNotNull(orderDTO.getCustomerId(), "Customer can not be null");
    }

}
