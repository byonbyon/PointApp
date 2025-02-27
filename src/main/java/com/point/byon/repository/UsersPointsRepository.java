package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.UsersPointsEntity;

public interface UsersPointsRepository  extends JpaRepository<UsersPointsEntity,Long> {

}
