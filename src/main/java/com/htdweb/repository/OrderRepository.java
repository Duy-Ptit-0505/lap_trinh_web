package com.htdweb.repository;

import com.htdweb.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity>  findAllByStatus(Integer status);
}
