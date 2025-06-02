package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.drawboard.DrawboardEndpoint;
import com.hjg.tomcat.example.echo.EchoEndpoint;
import jakarta.servlet.ServletContext;
import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-02 13:50
 */
//@Component
public class WebSocketRegisterEndpoint1 implements ApplicationListener<ApplicationReadyEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 确保在应用完全启动后执行
        ServletContext servletContext = event.getApplicationContext()
                .getBean(ServletContext.class);

        ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute(
                "jakarta.websocket.server.ServerContainer"
        );

        serverContainer.addEndpoint(
                ServerEndpointConfig.Builder.create(
                                EchoEndpoint.class,
                                "/websocket/echoProgrammatic")
                        .configurator(new SpringEndpointConfigurator())
                        .build()
        );

        serverContainer.addEndpoint(
                ServerEndpointConfig.Builder.create(
                                DrawboardEndpoint.class,
                                "/websocket/drawboard")
                        .configurator(new SpringEndpointConfigurator())
                        .build()
        );
    }
}
