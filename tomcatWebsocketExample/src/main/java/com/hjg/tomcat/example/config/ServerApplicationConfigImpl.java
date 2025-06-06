package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.drawboard.DrawboardEndpoint;
import com.hjg.tomcat.example.echo.EchoEndpoint;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 不再实现ServerApplicationConfig接口，在springboot环境下不再生效。
 * @Description
 * @Author hjg
 * @Date 2025-05-30 12:04
 */
public class ServerApplicationConfigImpl implements ServerApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerApplicationConfigImpl.class);

    public ServerApplicationConfigImpl() {
        logger.info("创建ServerApplicationConfigImpl");
    }

    /**
     * 获取实现了ServerEndpointConfig接口的类。
     * @param scanned The {@link Endpoint} implementations found in the application
     *
     * @return
     */
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {

        logger.info("getEndpointConfigs scanned size : {}", scanned.size());

        Set<ServerEndpointConfig> result = new HashSet<>();

        if (scanned.contains(EchoEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    EchoEndpoint.class,
                    "/websocket/echoProgrammatic")
                    .configurator(new SpringEndpointConfigurator())
                    .build());
        }

        if (scanned.contains(DrawboardEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    DrawboardEndpoint.class,
                    "/websocket/drawboard")
                    .configurator(new SpringEndpointConfigurator())
                    .build());
        }

        return result;
    }

    /**
     * 获取加上了@ServerEndpoint的类。
     * @param scanned The POJOs annotated with {@link ServerEndpoint} found in the application
     *
     * @return
     */
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        logger.info("getAnnotatedEndpointClasses scanned size : {}", scanned.size());

        // Deploy all WebSocket endpoints defined by annotations in the examples
        // web application. Filter out all others to avoid issues when running
        // tests on Gump
        Set<Class<?>> results = new HashSet<>();
        results = scanned.stream().filter(c -> !c.isAnnotationPresent(Component.class))
                .collect(Collectors.toSet());
//        for (Class<?> clazz : scanned) {
//            if (clazz.getPackage().getName().startsWith("websocket.")) {
//                results.add(clazz);
//            }
//        }

        logger.info("getAnnotatedEndpointClasses results size : {}", results.size());

        return results;
    }
}
