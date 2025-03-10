package com.point.byon.controller.docs;

import org.springframework.http.ResponseEntity;

import com.point.byon.dto.PointsResponseDTO;
import com.point.byon.dto.UsersPointsDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "POINT API", description = "포인트 적립, 사용, 사용취소를 할 수 있는 REST API 입니다.")
public interface PointControllerDocs {

	@Operation(summary  = "포인트 이력을 가져옵니다.", description = "")
	public ResponseEntity<PointsResponseDTO> getPointsHistory(String userId);
	
	@Operation(summary  = "포인트를 적립합니다.", description = "")
	public ResponseEntity<PointsResponseDTO> earnPoints(String userId, UsersPointsDTO dto);
	
	@Operation(summary  = "포인트를 사용합니다.", description = "")
	public ResponseEntity<PointsResponseDTO> usePoints(String userId, UsersPointsDTO dto);
	
	@Operation(summary  = "포인트를 사용을 취소합니다.", description = "")
	public ResponseEntity<PointsResponseDTO> cancelPoints(String userId, UsersPointsDTO dto);
}
