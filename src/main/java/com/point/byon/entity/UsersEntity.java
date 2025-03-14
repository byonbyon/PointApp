package com.point.byon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
	private String id;
	@Column(length = 255,nullable = false)
	private String password;
	@Column(length = 20,nullable = false)
	private String name;
	@Column(length = 10,nullable = false)
	private String role;

	private LocalDateTime regidate;
	
	@PrePersist
    public void prePersist() {
        this.regidate = LocalDateTime.now();
    }
}

