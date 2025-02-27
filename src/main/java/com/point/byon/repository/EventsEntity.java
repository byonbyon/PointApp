package com.point.byon.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventsEntity {

	@Id
	@SequenceGenerator(name = "SEQ_EVENTS",sequenceName="SEQ_EVENTS",allocationSize = 1,initialValue = 1 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_EVENTS")
	private long id;
	@Column(length = 20,nullable = false)
	private String name;

	private LocalDateTime startdate = LocalDateTime.now();

	private LocalDateTime expiredate = LocalDateTime.now().plus(12, ChronoUnit.MONTHS);
}

