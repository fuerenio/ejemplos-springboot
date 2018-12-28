package com.notasprogramacion.websockets.stomp.config;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
	
	@Override
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
		//System.out.println("SubscribeEventListener.onApplicationEvent() > user.name: "+sessionSubscribeEvent.getUser().getName());
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		System.out.println("SubscribeEventListener.onApplicationEvent() > sessionId: "+headerAccessor.getSessionAttributes().get("sessionId").toString());
	}
}