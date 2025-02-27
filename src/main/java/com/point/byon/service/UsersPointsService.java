package com.point.byon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.point.byon.repository.UsersPointsRepository;

@Service
public class UsersPointsService {

		@Autowired
		private UsersPointsRepository usersPointsRepo;

		/*public Page<BbsDto> UsersPointsAll(Pageable pageable){
			//리포 지토리 호출
			Page<BbsEntity> bbsEntities= bbsRepository.findAll(PageRequest.of(
					pageable.getPageNumber()-1, 
					pageable.getPageSize(), 
					pageable.getSort()));
			//Page<BbsEntity>를 Page<BbsDto>로 변환		
			return bbsEntities.map(bbsEntity->BbsDto.toDto(bbsEntity));
		}*/
}
