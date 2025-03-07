package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.OrdersDetailsEntity;

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
	
	private OrdersDTO ordersDto;
	
	private ProductsDTO productsDto;

	private LocalDateTime orderdate;

	private LocalDateTime canceldate;
	
	public OrdersDetailsEntity toEventsEntity() {
		
		return OrdersDetailsEntity.builder()
				.id(id)
				.ordersEntity(ordersDto.toOrdersEntity())
				.productsEntity(productsDto.toProductsEntity())
				.orderdate(orderdate)
				.canceldate(canceldate)
				.build();
	}
	
	public static OrdersDetailsDTO toOrdersDetailsDTO(OrdersDetailsEntity ent) {
		return OrdersDetailsDTO.builder()
				.id(ent.getId())
				.ordersDto(OrdersDTO.toOrdersDTO(ent.getOrdersEntity()))
				.productsDto(ProductsDTO.toProductsDTO(ent.getProductsEntity()))
				.orderdate(ent.getOrderdate())
				.canceldate(ent.getCanceldate())
				.build();
	}
}

