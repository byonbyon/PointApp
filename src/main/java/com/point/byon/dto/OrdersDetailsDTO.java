package com.point.byon.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDetailsDTO {

	private long id;
	
	private OrdersDTO ordersEntity;
	
	private ProductsDTO productsEntity;

	private LocalDateTime orderdate;

	private LocalDateTime canceldate;
}

