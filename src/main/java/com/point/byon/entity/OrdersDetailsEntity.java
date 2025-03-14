package com.point.byon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "OrdersDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDetailsEntity {

	@Id
	@SequenceGenerator(name = "SEQ_ORDERSDETAILS",sequenceName="SEQ_ORDERSDETAILS",allocationSize = 1,initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ORDERSDETAILS")
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="orders_id")
	private OrdersEntity ordersEntity;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="products_id")
	private ProductsEntity productsEntity;

	private LocalDateTime orderdate;

	private LocalDateTime canceldate;
	
	@PrePersist
    public void prePersist() {
        this.orderdate = LocalDateTime.now();
    }
}

