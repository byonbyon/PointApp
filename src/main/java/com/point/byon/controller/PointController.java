package com.point.byon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.point.byon.dto.UsersPointsDTO;
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

	/**
	 * 포인트이력을 습득.
	 * 엔트포인트 (GET)/users/1/points/history?pageNumber=1
	 * @param userId
	 * @param pageNumber
	 * @return
	 */
	@GetMapping("/users/{userId}/points/history")
	public Page<UsersPointsDTO> getPointsHistory(@PathVariable String userId, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
		Page<UsersPointsDTO> usersPoints = uPService.findAll(pageable);
		
		return usersPoints;
	}
	
	@ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleRuntimeException(IllegalStateException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Parameter Error: 파라미터에 문제가 있습니다.");
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Server Error");
    }
}
