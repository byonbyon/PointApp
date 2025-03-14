package com.point.byon.dto;

import java.util.List;

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
public class PointsResponseDTO {

	private List<UsersPointsDTO> historyDTO;
	
	private UsersPointsDTO resultDTO;
	
	private String errorCode;
}
