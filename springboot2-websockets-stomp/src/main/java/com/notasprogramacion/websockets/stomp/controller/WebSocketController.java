package com.notasprogramacion.websockets.stomp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

/**
 * Fuentes: 
 * Proyecto Spring Boot: 	https://www.devglan.com/sprint-boot/spring-session-stomp-websocket
 * 
 * @author gonzasilve
 *
 */
@Controller
public class WebSocketController {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/message")
    public void processMessageFromClient(@Payload String message, SimpMessageHeaderAccessor  headerAccessor) throws Exception {
		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		System.out.println("WebSocketController > "+sessionId+" envio el mensaje: "+new Gson().fromJson(message, Map.class).get("name"));
		headerAccessor.setSessionId(sessionId);
		messagingTemplate.convertAndSend("/topic/reply", new Gson().fromJson(message, Map.class).get("name"));
      
    }
}
