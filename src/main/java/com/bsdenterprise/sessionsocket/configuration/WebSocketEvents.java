package com.bsdenterprise.sessionsocket.configuration;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEvents {

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        //String chatRoomId = headers.getNativeHeader("chatRoomId").get(0);
        //headers.getSessionAttributes().put("chatRoomId", chatRoomId);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        //String chatRoomId = headers.getSessionAttributes().get("chatRoomId").toString();
    }
}
