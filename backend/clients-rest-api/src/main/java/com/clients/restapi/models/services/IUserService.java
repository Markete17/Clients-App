package com.clients.restapi.models.services;

import com.clients.restapi.models.entities.User;

public interface IUserService {
	
	public User findByUsername(String username);

}
