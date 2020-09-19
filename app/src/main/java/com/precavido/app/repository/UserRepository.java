package com.precavido.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.precavido.app.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	User findByEmail(String email);
	User findById(long id);
}
