package com.point.byon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.point.byon.common.PointConstants;
import com.point.byon.controller.docs.PointControllerDocs;
import com.point.byon.dto.PointsResponseDTO;
import com.point.byon.dto.UsersPointsDTO;
import com.point.byon.exception.NotEnoughPointsException;
import com.point.byon.service.UsersPointsService;

@RestController
public class PointController implements PointControllerDocs{
	
	@Autowired
	UsersPointsService uPService;
	
	//@Autowired
    //private MessageSource messageSource;

	/**
	 * 포인트이력을 습득.
	 * 엔드포인트 (GET)/users/1/points/history?pageNumber=1
	 * @param userId
	 * @param pageNumber
	 * @return
	 */
	@GetMapping("/users/{userId}/points/history")
	public ResponseEntity<PointsResponseDTO> getPointsHistory(@PathVariable String userId) {
		
		List<UsersPointsDTO> historyDTO = uPService.getPointsHistory(userId);
		
		PointsResponseDTO responseDTO = PointsResponseDTO.builder()
													.historyDTO(historyDTO)
													.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	

	/**
	 * 포인트 적립
	 * 엔드포인트 (POST)/users/1/points
	 * @param userId
	 * @param dto
	 * @return
	 */
	@PostMapping("/users/{userId}/points")
	public ResponseEntity<PointsResponseDTO> earnPoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {

		UsersPointsDTO resultDTO = uPService.earnPoints(dto);
		
		PointsResponseDTO responseDTO;
		if (resultDTO == null) {
			responseDTO = PointsResponseDTO.builder()
								.errorCode(PointConstants.ERROR_RUN_POINT_EARN_FAILED)
								.build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		List<UsersPointsDTO> historyDTO = uPService.getPointsHistory(userId);
		responseDTO = PointsResponseDTO.builder().resultDTO(resultDTO)
													.historyDTO(historyDTO)
													.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	/**
	 * 포인트 사용
	 * @param userId
	 * @param dto
	 * @return
	 */
	@PutMapping("/users/{userId}/points")
	public ResponseEntity<PointsResponseDTO> usePoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {
		
		int totalPoints = uPService.getTotalPoints(userId);
		PointsResponseDTO responseDTO;
		if (totalPoints + dto.getInitialpoints() < 0) {
			responseDTO = PointsResponseDTO.builder()
								.errorCode(PointConstants.ERROR_CHECK_POINT_NOT_ENOUGH)
								.build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}

		UsersPointsDTO resultDTO = uPService.usePoints(dto);
		if (resultDTO == null) {
			responseDTO = PointsResponseDTO.builder()
								.errorCode(PointConstants.ERROR_RUN_POINT_USE_FAILED)
								.build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		List<UsersPointsDTO> historyDTO = uPService.getPointsHistory(userId);
		responseDTO = PointsResponseDTO.builder().resultDTO(resultDTO)
													.historyDTO(historyDTO)
													.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	/**
	 * 포인트 적립 취소
	 * @param userId
	 * @param dto
	 * @return
	 */
	@PutMapping("/users/{userId}/points/cancel")
	public ResponseEntity<PointsResponseDTO> cancelPoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {
		
		UsersPointsDTO resultDTO = uPService.cancelPoints(dto);
		PointsResponseDTO responseDTO;
		if (resultDTO == null) {
			responseDTO = PointsResponseDTO.builder()
								.errorCode(PointConstants.ERROR_RUN_POINT_CANCEL_FAILED)
								.build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
		}
		
		List<UsersPointsDTO> historyDTO = uPService.getPointsHistory(userId);
		responseDTO = PointsResponseDTO.builder().resultDTO(resultDTO)
													.historyDTO(historyDTO)
													.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	/*@ExceptionHandler(NotEnoughPointsException.class)
    public ResponseEntity<String> handleRuntimeException(NotEnoughPointsException e) {
		String errorMessage = messageSource.getMessage("error.points.notenough", null, Locale.getDefault());
		//System.out.println(errorMessage);
		return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(String.format("ErrorCode : %s, ErrorMessage : %s", errorNotEnoughPoints, errorMessage));
    }*/
	
	@ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<PointsResponseDTO> handleRuntimeException(IllegalStateException e) {
		//String errorMessage = messageSource.getMessage("error.parameter.wrong", null, Locale.getDefault());
		return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(PointsResponseDTO.builder()
							.errorCode(PointConstants.ERROR_PARAMETER)
							.build());
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<PointsResponseDTO> handleRuntimeException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(PointsResponseDTO.builder()
					.errorCode(PointConstants.ERROR)
					.build());
    }
}
