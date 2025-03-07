package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.UsersEntity;

public interface UsersRepository  extends JpaRepository<UsersEntity,String> {

}
