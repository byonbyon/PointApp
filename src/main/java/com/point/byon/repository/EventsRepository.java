package com.point.byon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.point.byon.entity.EventsEntity;

public interface EventsRepository  extends JpaRepository<EventsEntity, Long> {

}
