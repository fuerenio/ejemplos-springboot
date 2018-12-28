package com.notasprogramacion.websockets.stomp.config;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = null;
			if (request instanceof ServletServerHttpRequest) {
				servletRequest = (ServletServerHttpRequest) request;
				attributes.put("custom-header", "gnz-customHeader");
			}
			Iterator<String> iterator = servletRequest.getHeaders().keySet().iterator();
			while(iterator.hasNext()) {
				String header = iterator.next();
				System.out.println("HttpHandshakeInterceptor > "+header + " : "+servletRequest.getHeaders().get(header) );
			}
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("sessionId", session.getId());
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}

}
