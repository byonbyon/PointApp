package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.UsersEntity;

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
public class UsersDTO {

	private String id;

	private String password;

	private String name;

	private String role;

	private LocalDateTime regidate;
	
	public UsersEntity toUsersEntity() {
		
		return UsersEntity.builder()
				.id(id)
				.password(password)
				.name(name)
				.role(role)
				.regidate(regidate)
				.build();
	}
	
	public static UsersDTO toUsersDTO(UsersEntity ent) {
		return UsersDTO.builder()
				.id(ent.getId())
				.password(ent.getPassword())
				.name(ent.getName())
				.role(ent.getRole())
				.regidate(ent.getRegidate())
				.build();
	}
}

