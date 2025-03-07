package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.OrdersEntity;

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
public class OrdersDTO {

	private long id;

	private int amount;

	private LocalDateTime regidate;
	
	public OrdersEntity toOrdersEntity() {
		
		return OrdersEntity.builder()
				.id(id)
				.amount(amount)
				.regidate(regidate)
				.build();
	}
	
	public static OrdersDTO toOrdersDTO(OrdersEntity ent) {
		return OrdersDTO.builder()
				.id(ent.getId())
				.amount(ent.getAmount())
				.regidate(ent.getRegidate())
				.build();
	}
}

