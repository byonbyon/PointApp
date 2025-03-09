package com.point.byon.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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

	private LocalDateTime startdate;

	private LocalDateTime expiredate;

	private LocalDateTime regidate;
	
	@PrePersist
    public void prePersist() {
        this.startdate = LocalDateTime.now().plus(7, ChronoUnit.DAYS);
        this.expiredate = LocalDateTime.now().plus(12, ChronoUnit.MONTHS);
        this.regidate = LocalDateTime.now();
    }
}

