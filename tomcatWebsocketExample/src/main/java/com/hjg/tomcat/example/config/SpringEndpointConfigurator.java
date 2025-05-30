package com.hjg.tomcat.example.config;

import com.hjg.tomcat.example.util.SpringContextUtil;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-29 11:26
 */
public class SpringEndpointConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) {
        return SpringContextUtil.getBean(endpointClass); // 从 Spring 容器获取实例
    }
}
