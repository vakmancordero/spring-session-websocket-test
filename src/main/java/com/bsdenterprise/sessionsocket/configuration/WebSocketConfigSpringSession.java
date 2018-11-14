package com.bsdenterprise.sessionsocket.configuration;

import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.session.ExpiringSession;

import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = false)
@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
@ConfigurationProperties(prefix = "test.relay")
public class WebSocketConfigSpringSession extends AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession> {

	private String host;
	private Integer port;

	protected void configureStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS();
	}

	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableStompBrokerRelay("/queue/", "/topic/")
			.setUserDestinationBroadcast("/topic/unresolved.user.dest")
			.setUserRegistryBroadcast("/topic/registry.broadcast")
			.setRelayHost(host)
			.setRelayPort(port);

		registry.setApplicationDestinationPrefixes("/test");
	}
}
