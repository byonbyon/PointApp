package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.UsersPointsEntity;

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

	private UsersDTO usersDto;

	private int pointvalue;

	private LocalDateTime regidate;

	private LocalDateTime expiredate;

	private EventsDTO eventsDto;

	private OrdersDTO ordersDto;
	
	public UsersPointsEntity toUsersPointsEntity() {
		
		return UsersPointsEntity.builder()
				.pointkey(pointkey)
				.usersEntity(usersDto.toUsersEntity())
				.pointvalue(pointvalue)
				.regidate(regidate)
				.expiredate(expiredate)
				.eventsEntity(eventsDto.toEventsEntity())
				.ordersEntity(ordersDto.toOrdersEntity())
				.build();
	}
	
	public static UsersPointsDTO toUsersPointsDTO(UsersPointsEntity ent) {
		return UsersPointsDTO.builder()
				.pointkey(ent.getPointkey())
				.usersDto(UsersDTO.toUsersDTO(ent.getUsersEntity()))
				.pointvalue(ent.getPointvalue())
				.regidate(ent.getRegidate())
				.expiredate(ent.getExpiredate())
				.eventsDto(EventsDTO.toEventsDTO(ent.getEventsEntity()))
				.ordersDto(OrdersDTO.toOrdersDTO(ent.getOrdersEntity()))
				.build();
	}
}

