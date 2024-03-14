package org.example.orderservice.repositories;

import org.example.orderservice.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> getOrderByUserId(Long userId);

}
