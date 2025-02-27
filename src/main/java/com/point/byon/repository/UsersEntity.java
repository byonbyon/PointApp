package com.point.byon.repository;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersEntity {

	@Id
	@Column(length = 20)
	private String username;
	@Column(length = 255,nullable = false)
	private String password;
	@Column(length = 20,nullable = false)
	private String name;
	@Column(length = 20,nullable = false)
	private String role;

	private LocalDateTime regidate = LocalDateTime.now();
}

