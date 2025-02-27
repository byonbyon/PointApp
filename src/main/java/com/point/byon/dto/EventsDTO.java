package com.point.byon.dto;

import java.time.LocalDateTime;

import com.point.byon.entity.EventsEntity;

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
public class EventsDTO {

	private long id;

	private String name;

	private LocalDateTime startdate;

	private LocalDateTime expiredate;
	
	public EventsEntity toEventsEntity() {
		
		return EventsEntity.builder()
				.id(id)
				.name(name)
				.startdate(startdate)
				.expiredate(expiredate)
				.build();
	}
	
	public EventsDTO toEventsDTO(EventsEntity ent) {
		return EventsDTO.builder()
				.id(ent.getId())
				.name(ent.getName())
				.startdate(ent.getStartdate())
				.expiredate(ent.getExpiredate())
				.build();
	}
}

