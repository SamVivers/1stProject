package com.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
