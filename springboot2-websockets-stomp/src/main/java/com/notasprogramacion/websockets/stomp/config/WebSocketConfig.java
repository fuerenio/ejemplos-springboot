package com.notasprogramacion.websockets.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Override
	 public void configureMessageBroker(MessageBrokerRegistry config) {
		//Aca para suscribirse
	  config.enableSimpleBroker("/topic/", "/queue/");
	  //Aca para enviar ejemplo /app/message
	  config.setApplicationDestinationPrefixes("/app");
	 }
	 
	 @Override
	 public void registerStompEndpoints(StompEndpointRegistry registry) {
	  registry.addEndpoint("/websocketApp")
	  .setAllowedOrigins("*")
	  .addInterceptors(new HttpHandshakeInterceptor());
	 }

}