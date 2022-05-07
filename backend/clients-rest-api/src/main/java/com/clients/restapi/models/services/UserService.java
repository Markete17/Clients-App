package com.clients.restapi.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clients.restapi.models.dao.IUserDAO;
import com.clients.restapi.models.entities.User;

@Service
public class UserService implements UserDetailsService, IUserService{
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private IUserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDAO.findByUsername(username);
		
		if(user==null) {
			String error = "Login error: User: ".concat(username).concat(" not found.");
			logger.error(error);
			throw new UsernameNotFoundException(error);
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getName()))
				.peek(auth -> logger.info("Role: "+auth.getAuthority()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		return this.userDAO.findByUsername(username);
	}

}
