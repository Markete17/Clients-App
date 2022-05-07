package com.clients.restapi.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clients.restapi.models.entities.User;

public interface IUserDAO extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
	/*@Query("select u from User u where u.username=?1 and u.email?2")
	public User findByUsernameEmail(String username);*/
}
