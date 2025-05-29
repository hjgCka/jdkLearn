package com.hjg.wscontroller;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-27 20:17
 */
@ServerEndpoint(value = "/ws/chat/{userId}")
@Component
public class ChatEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatEndpoint.class);

    // 客户端计数器
    private static final AtomicInteger counter = new AtomicInteger(0);
    // 客户端websocket连接集合
    private static final Set<ChatEndpoint> connections = new CopyOnWriteArraySet<ChatEndpoint>();
    // WebSocket会话对象
    private Session session = null;
    // 客户端编号
    private Integer number = 0;

    public ChatEndpoint() {
        number = counter.incrementAndGet();
        logger.info("创建ChatEndpoint对象, hashCode={}", this.hashCode());
    }

    /**
     * 客户端建立websocket连接
     * @param session
     */
    @OnOpen
    public void start(Session session, @PathParam("userId") String userId, @PathParam("username") String username) {
        this.session = session;
        connections.add(this);
        try {
            session.getBasicRemote().sendText(new StringBuffer().append("Hello: ").append(number).toString());
        } catch (Exception e) {
            logger.error("发送消息时异常", e);
        }
    }

    /**
     * 客户端断开websocket连接
     */
    @OnClose
    public void close(@PathParam("userId") String userId) {
        try {
            this.session.close();
        } catch (Exception e) {
            logger.error("关闭连接时异常", e);
        } finally {
            connections.remove(this);
        }
    }

    /**
     * 接收客户端发送的消息
     * @param message
     */
    @OnMessage
    public void message(String message, @PathParam("userId") String userId, Session session) {
        logger.info("收到消息, userId={}, message={}", userId, message);
        for(ChatEndpoint client : connections) {
            synchronized (client) {
                try {
                    client.session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    logger.error("发送客户端消息时异常", e);
                }
            }
        }
    }

    @OnError
    public void error(Throwable t) {
        logger.error("客户端连接异常", t);
    }

    public static Set<ChatEndpoint> getConnections() {
        return connections;
    }

    public Session getSession() {
        return session;
    }
}
