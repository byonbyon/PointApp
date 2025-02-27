package com.point.byon.repository;

import java.time.LocalDateTime;

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

@Entity(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersEntity {

	@Id
	@SequenceGenerator(name = "SEQ_ORDERS",sequenceName="SEQ_ORDERS",allocationSize = 1,initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ORDERS")
	private long id;
	@Column(length = 20,nullable = false)
	private int amount;

	private LocalDateTime regidate = LocalDateTime.now();
}

