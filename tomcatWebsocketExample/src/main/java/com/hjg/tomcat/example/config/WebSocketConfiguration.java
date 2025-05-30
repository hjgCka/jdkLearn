package com.hjg.tomcat.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-30 1:04
 */
@Configuration
public class WebSocketConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
