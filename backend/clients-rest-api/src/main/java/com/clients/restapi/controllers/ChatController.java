package com.clients.restapi.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.clients.restapi.models.entities.Message;

@Controller
public class ChatController {
	
	private String[] colors = {"red","blue","green","pink","magenta","orange","purple"};
	
	@MessageMapping("/message")
	@SendTo("/chat/message")
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		
		if(message.getType().equals("NEW_USER")) {
			message.setColor(colors[new Random().nextInt(this.colors.length)]);
			message.setText("New user");
		}
		return message;
	}
	
	@MessageMapping("/writing")
	@SendTo("/chat/writing")
	public String isWriting(String username) {
		return username.concat(" is writing...");
	}

}
