package com.point.byon.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsEntity {

	@Id
	@SequenceGenerator(name = "SEQ_PRODUCTS",sequenceName="SEQ_PRODUCTS",allocationSize = 1,initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PRODUCTS")
	private long id;
	@Column(length = 500,nullable = false)
	private String name;
	@Column(length = 20,nullable = false)
	private int price;

	private LocalDateTime startdate = LocalDateTime.now().plus(7, ChronoUnit.DAYS);

	private LocalDateTime expiredate = LocalDateTime.now().plus(12, ChronoUnit.MONTHS);

	private LocalDateTime regidate = LocalDateTime.now();
}

