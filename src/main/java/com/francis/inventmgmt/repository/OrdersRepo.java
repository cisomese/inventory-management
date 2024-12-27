package com.francis.inventmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francis.inventmgmt.model.Order;

public interface OrdersRepo extends JpaRepository<Order,Long>{
    
}
