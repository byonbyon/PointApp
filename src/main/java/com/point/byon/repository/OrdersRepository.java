package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.OrdersEntity;

public interface OrdersRepository  extends JpaRepository<OrdersEntity,Long> {

}
