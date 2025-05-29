package com.hjg.wscontroller;

import com.hjg.service.PersonService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这里还可以加上@Scope("prototype")注解，每次获取bean时都新建一个，同时能使用spring的能力。
 * 不过阅读了一下代码，似乎没有加上这个注解的，虽然加上会更符合tomcat处理websocket连接的行为。
 * 如果不加上注解，成员变量需要是线程安全的。
 * @Description
 * @Author hjg
 * @Date 2025-05-27 20:17
 */
@ServerEndpoint(value = "/ws/chat/{userId}",
        configurator = SpringEndpointConfigurator.class,
        encoders = {EndpointEncoder.class},
        decoders = {EndpointDecoder.class}
)
@Component
public class ChatEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatEndpoint.class);

    @Autowired
    private PersonService personService;

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
    public void start(Session session, @PathParam("userId") String userId) {
        logger.info("建立websocket连接, userId={}, person={}", userId, personService.findById(2));
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
    public void message(WsMsg message, @PathParam("userId") String userId, Session session) {
        logger.info("收到消息, userId={}, message={}", userId, message);
        for(ChatEndpoint client : connections) {
            synchronized (client) {
                try {
                    //配置了编码器之后，可以不再用sendText方法
                    client.session.getBasicRemote().sendObject(message);
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
