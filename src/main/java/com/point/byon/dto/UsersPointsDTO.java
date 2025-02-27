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
public class UsersPointsDTO {

	private long pointkey;

	private UsersDTO usersEntity;

	private int pointvalue;

	private LocalDateTime regidate;

	private LocalDateTime expiredate;

	private EventsDTO eventsEntity;

	private OrdersDTO ordersEntity;
}

