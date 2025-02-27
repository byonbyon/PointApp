package com.point.byon.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "UsersPoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersPointsEntity {

	@Id
	@SequenceGenerator(name = "SEQ_USERSPOINTS",sequenceName="SEQ_USERSPOINTS",allocationSize = 1,initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USERSPOINTS")
	private long pointkey;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="users_username")
	private UsersEntity usersEntity;
	
	@Column(length = 20,nullable = false)
	private int pointvalue;

	private LocalDateTime regidate = LocalDateTime.now();

	private LocalDateTime expiredate = LocalDateTime.now().plus(12, ChronoUnit.MONTHS);
	
	@ManyToOne
	@JoinColumn(name="events_id")
	private EventsEntity eventsEntity;
	
	@OneToOne
	@JoinColumn(name="orders_id")
	private OrdersEntity ordersEntity;
}

