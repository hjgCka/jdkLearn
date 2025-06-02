package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.drawboard.DrawboardEndpoint;
import com.hjg.tomcat.example.echo.EchoEndpoint;
import jakarta.servlet.ServletContext;
import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
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
        ServerEndpointExporter exporter = new ServerEndpointExporter();
        return exporter;
    }

    /*@Bean
    public ApplicationRunner registerProgrammaticEndpoints(WebApplicationContext ctx) {
        return args -> {
            ServletContext servletContext = ctx.getServletContext();
            ServerContainer container = (ServerContainer)
                    servletContext.getAttribute("jakarta.websocket.server.ServerContainer");

            container.addEndpoint(
                    ServerEndpointConfig.Builder.create(
                                    EchoEndpoint.class,
                                    "/websocket/echoProgrammatic")
                            .configurator(new SpringEndpointConfigurator())
                            .build()
            );

            container.addEndpoint(
                    ServerEndpointConfig.Builder.create(
                                    DrawboardEndpoint.class,
                                    "/websocket/drawboard")
                            .configurator(new SpringEndpointConfigurator())
                            .build()
            );
        };
    }*/

    /*@Bean
    public ServerEndpointConfig echoEndpointConfig() {
        return ServerEndpointConfig.Builder.create(
                        EchoEndpoint.class,
                        "/websocket/echoProgrammatic")
                .configurator(new SpringEndpointConfigurator())
                .build();
    }

    @Bean
    public ServerEndpointConfig drawBoardEndpointConfig() {
        return ServerEndpointConfig.Builder.create(
                        DrawboardEndpoint.class,
                        "/websocket/drawboard")
                .configurator(new SpringEndpointConfigurator())
                .build();
    }*/

}
