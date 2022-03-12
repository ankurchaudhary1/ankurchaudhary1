package com.ssg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "login_detail", schema = "SSG")
@Component
public class UserRegistrationEntity {


	@Id
	@Column(name = "username", nullable = false)
	private String userName;
	@Column(name = "password", nullable = false)
	private String password;

	public UserRegistrationEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
