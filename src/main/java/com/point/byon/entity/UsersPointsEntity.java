package com.point.byon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
	@JoinColumn(name="users_id")
	private UsersEntity usersEntity;
	
	@Column(length = 20)
	private int initialpoints;
	
	@Column(length = 20)
	private int usablepoints;

	private LocalDateTime regidate;

	private LocalDateTime expiredate;
	
	@OneToOne
	@JoinColumn(name="orders_id")
	private OrdersEntity ordersEntity;
	
	private String relatedPointkeys;
	
	@Column(length = 2000)
	private String etc;
	
	@PrePersist
    public void prePersist() {
        this.regidate = LocalDateTime.now();
    }
}

