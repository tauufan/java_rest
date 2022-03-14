package com.bjb.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjb.api.model.User;

public interface UsersRepository extends JpaRepository<User, Long>{
	public List<User> findByUsername(String username);
	public User findByUsername_(String username);
}
