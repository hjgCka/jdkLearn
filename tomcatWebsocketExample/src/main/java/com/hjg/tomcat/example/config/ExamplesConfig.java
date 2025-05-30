package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.drawboard.DrawboardEndpoint;
import com.hjg.tomcat.example.echo.EchoEndpoint;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-30 1:04
 */
public class ExamplesConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<>();

        if (scanned.contains(EchoEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    EchoEndpoint.class,
                    "/websocket/echoProgrammatic").build());
        }

        if (scanned.contains(DrawboardEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    DrawboardEndpoint.class,
                    "/websocket/drawboard").build());
        }

        return result;
    }


    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        // Deploy all WebSocket endpoints defined by annotations in the examples
        // web application. Filter out all others to avoid issues when running
        // tests on Gump
        Set<Class<?>> results = new HashSet<>();
        results = results.stream().filter(c -> !c.isAnnotationPresent(Component.class))
                .collect(Collectors.toSet());
//        for (Class<?> clazz : scanned) {
//            if (clazz.getPackage().getName().startsWith("websocket.")) {
//                results.add(clazz);
//            }
//        }
        return results;
    }
}
