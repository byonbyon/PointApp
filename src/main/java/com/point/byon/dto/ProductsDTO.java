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
public class ProductsDTO {

	private long id;

	private String name;

	private int price;

	private LocalDateTime startdate;

	private LocalDateTime expiredate;

	private LocalDateTime regidate;
}

