package com.point.byon.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.point.byon.dto.UsersPointsDTO;
import com.point.byon.entity.UsersEntity;
import com.point.byon.entity.UsersPointsEntity;
import com.point.byon.repository.UsersPointsRepository;

@Service
public class UsersPointsService {

		@Autowired
		private UsersPointsRepository usersPointsRepo;
		
		public List<UsersPointsDTO> getPointsHistory(String userId) {
			List<UsersPointsEntity> points = usersPointsRepo.findByUsersEntityOrderByExpiredateAsc(UsersEntity.builder().id(userId).build());
			
			return points.stream().map(usersPointsEntity->UsersPointsDTO.toUsersPointsDTO(usersPointsEntity)).toList();
		}
		
		/**
		 * 포인트를 적립한다.
		 * @param dto
		 * @return
		 */
		@Transactional
		public UsersPointsDTO earnPoints(UsersPointsDTO dto) {
			UsersPointsEntity savedEntity = usersPointsRepo.save(dto.toUsersPointsEntity());
			
			return UsersPointsDTO.toUsersPointsDTO(savedEntity);
		}
		
		public int getTotalPoints(String userId) {
			return usersPointsRepo.getTotalPoints(userId);
		}

		/**
		 * 포인트를 사용한다.
		 * @param dto
		 * @return
		 */
		@Transactional
		public UsersPointsDTO usePoints(UsersPointsDTO dto) {
			List<UsersPointsEntity> points = usersPointsRepo.getPoints(dto.getUsersDto().getId());
			int remainingToUse = dto.getInitialpoints();
	        StringBuffer relativeKeys = new StringBuffer();
			for (UsersPointsEntity point : points) {
				relativeKeys.append(point.getPointkey()).append(",");
				
	        	if (remainingToUse + point.getUsablepoints() < 0) {
	        		remainingToUse = remainingToUse + point.getUsablepoints();
	        		point.setUsablepoints(0);
	        		point.setExpiredate(LocalDateTime.now());
	        		usersPointsRepo.save(point);
	        	} else {
	        		point.setUsablepoints(remainingToUse + point.getUsablepoints());
	        		usersPointsRepo.save(point);
	        		break;
	        	}
	        }
			relativeKeys.deleteCharAt(relativeKeys.length() - 1);
			dto.setRelatedPointkeys(relativeKeys.toString());
	        UsersPointsEntity savedUsersPoint = usersPointsRepo.save(dto.toUsersPointsEntity());
	        return UsersPointsDTO.toUsersPointsDTO(savedUsersPoint);
		}
		
		/**
		 * 포인트를 사용을 일부 취소한다.
		 * @param dto
		 * @return
		 */
		@Transactional
		public UsersPointsDTO cancelPoints(UsersPointsDTO dto) {
			System.out.println(dto.getRelatedPointkeys());
			UsersPointsEntity cancelPoint = usersPointsRepo.save(dto.toUsersPointsEntity());
			
			UsersPointsEntity usePoint = usersPointsRepo.findByPointkey(Long.parseLong(dto.getRelatedPointkeys()));
			
			List<Long> pointKeyList = new ArrayList<>();
			if (usePoint.getRelatedPointkeys() != null) {
				String[] keys = usePoint.getRelatedPointkeys().split(",");
				for (String key : keys) {
					pointKeyList.add(Long.parseLong(key));
				}
			}

			List<UsersPointsEntity> relativeUsersPoints = usersPointsRepo.findByPointkeyInOrderByExpiredateAsc(pointKeyList);
			int remainingToCancel = dto.getInitialpoints();
			for (UsersPointsEntity relPo : relativeUsersPoints) {
				if (relPo.getExpiredate().isBefore(LocalDateTime.now())) {
					remainingToCancel = remainingToCancel - relPo.getInitialpoints();
					UsersPointsEntity newPoint = UsersPointsEntity.builder()
													.usersEntity(dto.getUsersDto().toUsersEntity())
													.initialpoints(relPo.getInitialpoints())
													.usablepoints(relPo.getInitialpoints())
													.expiredate(LocalDateTime.now().plusYears(1))
													.etc("포인트를 사용을 일부 취소 - 포인트키 만기 - " + relPo.getPointkey())
													.build();
					usersPointsRepo.save(newPoint);
				} else {
					relPo.setUsablepoints(relPo.getUsablepoints() + remainingToCancel);
					usersPointsRepo.save(relPo);
				}
			}
			return UsersPointsDTO.toUsersPointsDTO(cancelPoint);
		}
}
