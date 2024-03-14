package org.example.orderservice;

import org.example.orderservice.models.Order;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;

import javax.management.Query;
import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

//    @Autowired
//    OrderRepository orderRepository;
//
//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args -> {
//            for (int i = 0; i < 10; i++) {
//                Order order = Order.builder()
//                        .price(2000 + i)
//                        .createAt(LocalDate.now())
//                        .build();
//                orderRepository.save(order);
//            }
//        };
//    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return  args -> {

        };
    }
}
