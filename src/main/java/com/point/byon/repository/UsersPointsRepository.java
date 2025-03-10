package com.point.byon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.point.byon.entity.UsersEntity;
import com.point.byon.entity.UsersPointsEntity;

public interface UsersPointsRepository  extends JpaRepository<UsersPointsEntity,Long> {

	@Query("SELECT COALESCE(SUM(usablepoints), 0) FROM UsersPoints WHERE usersEntity.id = :userId AND expiredate >= NOW()")
	Integer getTotalPoints(@Param("userId") String userId);

	@Query("SELECT up FROM UsersPoints up WHERE usersEntity.id = :userId AND up.expiredate >= NOW() ORDER BY up.expiredate ASC")
	List<UsersPointsEntity> getPoints(@Param("userId") String userId);

	List<UsersPointsEntity> findByUsersEntityOrderByExpiredateAsc(UsersEntity id);
	
	List<UsersPointsEntity> findByPointkeyInOrderByExpiredateAsc(List<Long> pointKeys);

	//UsersPointsEntity findByPointkey(long relatedPointkey);
}
