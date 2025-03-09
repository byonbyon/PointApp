package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.OrdersDetailsEntity;

public interface OrdersDetailsRepository  extends JpaRepository<OrdersDetailsEntity,Long> {

}
