package com.hjg.tomcat.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-30 1:03
 */
@SpringBootApplication
public class WebsocketExampleApp {

    /**
     * 访问方式为：
     * http://localhost:8088/ws-client/index.xhtml
     * 原理依然是配置了资源处理器。
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WebsocketExampleApp.class, args);
    }
}
