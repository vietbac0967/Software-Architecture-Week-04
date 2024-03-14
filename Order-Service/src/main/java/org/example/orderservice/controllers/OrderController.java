package org.example.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.models.Order;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }
    @PostMapping("")
    public Order createOrder(@RequestBody Order order) {
        order.setCreateAt(LocalDate.now());
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public List<Order> getOrdersByUserId(@PathVariable("id") Long id) {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders.stream().filter(x -> x.getUserId().equals(id)).toList();
    }
}
