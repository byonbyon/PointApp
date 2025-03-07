package com.point.byon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.point.byon.dto.UsersPointsDTO;
import com.point.byon.entity.UsersPointsEntity;
import com.point.byon.repository.UsersPointsRepository;

@Service
public class UsersPointsService {

		@Autowired
		private UsersPointsRepository usersPointsRepo;

		public Page<UsersPointsDTO> findAll(Pageable pageable){
			Page<UsersPointsEntity> usersPointsEntities= usersPointsRepo.findAll(PageRequest.of(
					pageable.getPageNumber()-1, 
					pageable.getPageSize(), 
					pageable.getSort()));	
			return usersPointsEntities.map(usersPointsEntity->UsersPointsDTO.toUsersPointsDTO(usersPointsEntity));
		}
}
