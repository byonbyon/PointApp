package com.point.byon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.point.byon.repository.UsersRepository;

@Service
public class IndexService {

	@Autowired
	UsersRepository usersRepo;
	
	public void prepare() {
		
	}
}
