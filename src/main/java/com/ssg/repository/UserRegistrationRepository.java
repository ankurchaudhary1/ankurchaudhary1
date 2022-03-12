package com.ssg.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssg.model.UserRegistrationEntity;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationEntity, String> {

	UserRegistrationEntity findByUserName(String username);
}