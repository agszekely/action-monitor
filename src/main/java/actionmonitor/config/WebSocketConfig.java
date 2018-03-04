package actionmonitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import actionmonitor.Application;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	private static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
	
	private static final String MESSAGE_ENDPOINT = "/message";
	private static final String APP_DESTINATION_PREFIX = "/app";
	private static final String STOMP_ENDPOINT= "/actionmonitor";
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	logger.info("Initializing messageBroker with simpleBorker : '{}' and applicationPrefix : '{}'", MESSAGE_ENDPOINT, APP_DESTINATION_PREFIX);
        config.enableSimpleBroker(MESSAGE_ENDPOINT);
        config.setApplicationDestinationPrefixes(APP_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	logger.info("Initializing STOMP endpoint : '{}'", STOMP_ENDPOINT);
        registry.addEndpoint(STOMP_ENDPOINT).withSockJS();
    }

}