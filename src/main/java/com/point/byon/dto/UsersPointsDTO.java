package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.UsersPointsEntity;

import jakarta.validation.constraints.Size;
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

	private int initialpoints;
	
	private int usablepoints;

	private LocalDateTime regidate;

	private LocalDateTime expiredate;

	private OrdersDTO ordersDto;
	
	private String relatedPointkeys;
	
	@Size(max = 1000, message = "{etc.maxlength.error}")
	private String etc;
	
	public UsersPointsEntity toUsersPointsEntity() {
		UsersPointsEntity ent = UsersPointsEntity.builder()
									.pointkey(pointkey)
									.usersEntity(usersDto.toUsersEntity())
									.initialpoints(initialpoints)
									.usablepoints(usablepoints)
									.regidate(regidate)
									.expiredate(expiredate)
									.relatedPointkeys(relatedPointkeys)
									.etc(etc)
									.build();
		if (ordersDto != null) {
			ent.setOrdersEntity(ordersDto.toOrdersEntity());
		}

		return ent;
	}
	
	public static UsersPointsDTO toUsersPointsDTO(UsersPointsEntity ent) {
		UsersPointsDTO dto = UsersPointsDTO.builder()
									.pointkey(ent.getPointkey())
									.usersDto(UsersDTO.toUsersDTO(ent.getUsersEntity()))
									.initialpoints(ent.getInitialpoints())
									.usablepoints(ent.getUsablepoints())
									.regidate(ent.getRegidate())
									.expiredate(ent.getExpiredate())
									.relatedPointkeys(ent.getRelatedPointkeys())
									.etc(ent.getEtc())
									.build();
		if (ent.getOrdersEntity() != null) {
			dto.setOrdersDto(OrdersDTO.toOrdersDTO(ent.getOrdersEntity()));
		}

		return dto;
	}
}

