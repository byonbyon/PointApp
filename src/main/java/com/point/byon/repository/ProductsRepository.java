package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.ProductsEntity;

public interface ProductsRepository  extends JpaRepository<ProductsEntity,Long> {

}
