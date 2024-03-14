package org.example.orderservice.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("Order")
public class Order implements Serializable {
    @Id
    @Indexed
    private Long id;
    private double price;
    private LocalDate createAt;
    private Long userId;
}
