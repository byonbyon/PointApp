package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.ProductsEntity;

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
public class ProductsDTO {

	private long id;

	private String name;

	private int price;

	private LocalDateTime startdate;

	private LocalDateTime expiredate;

	private LocalDateTime regidate;
	
	public ProductsEntity toProductsEntity() {
		
		return ProductsEntity.builder()
				.id(id)
				.name(name)
				.price(price)
				.startdate(startdate)
				.expiredate(expiredate)
				.regidate(regidate)
				.build();
	}
	
	public static ProductsDTO toProductsDTO(ProductsEntity ent) {
		return ProductsDTO.builder()
				.id(ent.getId())
				.name(ent.getName())
				.price(ent.getPrice())
				.startdate(ent.getStartdate())
				.expiredate(ent.getExpiredate())
				.regidate(ent.getRegidate())
				.build();
	}
}

