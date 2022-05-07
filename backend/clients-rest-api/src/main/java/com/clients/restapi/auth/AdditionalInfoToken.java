package com.clients.restapi.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.clients.restapi.models.entities.User;
import com.clients.restapi.models.services.IUserService;

@Component
public class AdditionalInfoToken implements TokenEnhancer {
	
	@Autowired
	private IUserService userService;

	// Para añadir información al token del usuario
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		User user = userService.findByUsername(authentication.getName());
		info.put("username ",authentication.getName());
		info.put("id",user.getId());
		info.put("first_name",user.getFirstName());
		info.put("last_name",user.getLastName());
		info.put("email",user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
