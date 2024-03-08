package org.example.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.models.Order;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    @GetMapping
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
