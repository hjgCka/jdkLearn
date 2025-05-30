package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.drawboard.DrawboardEndpoint;
import com.hjg.tomcat.example.echo.EchoEndpoint;
import jakarta.servlet.ServletContext;
import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.ServletContextInitializer;
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

    //通过bean注册
    @Bean
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
    }

    /*@Bean
    public ServletContextInitializer webSocketInitializer() {
        return WebSocketConfiguration::onStartup;
    }*/

    private static void onStartup(ServletContext servletContext) {
        ServerContainer container = (ServerContainer)
                servletContext.getAttribute("jakarta.websocket.server.ServerContainer");

        ServerEndpointConfig echoConfig = ServerEndpointConfig.Builder.create(
                        EchoEndpoint.class,
                        "/websocket/echoProgrammatic")
                .configurator(new SpringEndpointConfigurator())
                .build();

        ServerEndpointConfig drawConfig = ServerEndpointConfig.Builder.create(
                        DrawboardEndpoint.class,
                        "/websocket/drawboard")
                .configurator(new SpringEndpointConfigurator())
                .build();

        try {
            container.addEndpoint(echoConfig);
            container.addEndpoint(drawConfig);
        } catch (Exception e) {
            logger.error("注册编程式端点时异常", e);
        }
    }

}
