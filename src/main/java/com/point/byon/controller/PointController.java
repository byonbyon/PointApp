package com.point.byon.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.point.byon.dto.PointsResponseDTO;
import com.point.byon.dto.UsersPointsDTO;
import com.point.byon.exception.NotEnoughPointsException;
import com.point.byon.service.UsersPointsService;

@RestController
public class PointController {
	
	@Autowired
	UsersPointsService uPService;
	
	//페이징 관련 상수 주입받기
	@Value("${users.points.pageSize}")
	private int pageSize;
	@Value("${users.points.blockPage}")
	private int blockPage;
	
	//에러메시지
	@Value("${points.notenough}")
	private String errorNotEnoughPoints;
	
	@Autowired
    private MessageSource messageSource;

	/**
	 * 포인트이력을 습득.
	 * 엔드포인트 (GET)/users/1/points/history?pageNumber=1
	 * @param userId
	 * @param pageNumber
	 * @return
	 */
	@GetMapping("/users/{userId}/points/history")
	public List<UsersPointsDTO> getPointsHistory(@PathVariable String userId) {
		
		List<UsersPointsDTO> pointsHistory = uPService.getPointsHistory(userId);
		System.out.println("pointsHistory 수 : " + pointsHistory.size());
		
		return pointsHistory;
	}
	

	/**
	 * 포인트 적립
	 * 엔드포인트 (POST)/users/1/points
	 * @param userId
	 * @param dto
	 * @return
	 */
	@PostMapping("/users/{userId}/points")
	public PointsResponseDTO earnPoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {
		
		//System.out.println(dto);
		//System.out.println(dto.getPointvalue());
		//System.out.println(dto.getUsersDto().getId());
		//System.out.println(dto.getEventsDto().getId());
		UsersPointsDTO resultDTO = uPService.earnPoints(dto);
		List<UsersPointsDTO> historyDTO = getPointsHistory(userId);
		
		return PointsResponseDTO.builder().resultDTO(resultDTO)
										.historyDTO(historyDTO)
										.build();
	}
	
	@PutMapping("/users/{userId}/points")
	public PointsResponseDTO usePoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {
		
		int totalPoints = uPService.getTotalPoints(userId);
		if (totalPoints + dto.getInitialpoints() < 0) {
			throw new NotEnoughPointsException("Not Enough Points.");
		}

		UsersPointsDTO resultDTO = uPService.usePoints(dto);
		List<UsersPointsDTO> historyDTO = getPointsHistory(userId);
		return PointsResponseDTO.builder().resultDTO(resultDTO)
										.historyDTO(historyDTO)
										.build();
	}
	
	@PutMapping("/users/{userId}/points/cancel")
	public PointsResponseDTO cancelPoints(@PathVariable String userId, @RequestBody UsersPointsDTO dto) {
		
		UsersPointsDTO resultDTO = uPService.cancelPoints(dto);

		List<UsersPointsDTO> historyDTO = getPointsHistory(userId);
		return PointsResponseDTO.builder().resultDTO(resultDTO)
										.historyDTO(historyDTO)
										.build();
	}
	
	@ExceptionHandler(NotEnoughPointsException.class)
    public ResponseEntity<String> handleRuntimeException(NotEnoughPointsException e) {
		String errorMessage = messageSource.getMessage("error.points.notenough", null, Locale.getDefault());
		//System.out.println(errorMessage);
		return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(String.format("ErrorCode : %s, ErrorMessage : %s", errorNotEnoughPoints, errorMessage));
    }
	
	@ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleRuntimeException(IllegalStateException e) {
		String errorMessage = messageSource.getMessage("error.parameter.wrong", null, Locale.getDefault());
		return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(String.format("ErrorCode : %s, ErrorMessage : %s", HttpStatus.BAD_REQUEST, errorMessage));
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Server Error");
    }
}
