package com.microservice.orderservices.Repositories;

import com.microservice.orderservices.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReposity extends JpaRepository<Order,Long> {
}
