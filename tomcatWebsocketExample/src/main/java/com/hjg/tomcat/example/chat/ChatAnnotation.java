package com.hjg.tomcat.example.chat;

import com.hjg.tomcat.example.config.SpringEndpointConfigurator;
import com.hjg.tomcat.example.util.HTMLFilter;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-30 1:05
 */
@Scope("prototype")
@Component
@ServerEndpoint(value = "/websocket/chat", configurator = SpringEndpointConfigurator.class)
public class ChatAnnotation {
    private static final Logger log = LoggerFactory.getLogger(ChatAnnotation.class);

    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections = new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;
    /*
     * The queue of messages that may build up while another message is being sent. The thread that sends a message is
     * responsible for clearing any queue that builds up while that message is being sent.
     */
    private Queue<String> messageBacklog = new ArrayDeque<>();
    private boolean messageInProgress = false;

    public ChatAnnotation() {
        log.info("创建ChatAnnotation");
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }


    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(message);
    }


    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "has disconnected.");
        broadcast(message);
    }


    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s", nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }


    /*
     * synchronized blocks are limited to operations that are expected to be quick. More specifically, messages are not
     * sent from within a synchronized block.
     */
    private void sendMessage(String msg) throws IOException {

        synchronized (this) {
            if (messageInProgress) {
                messageBacklog.add(msg);
                return;
            } else {
                messageInProgress = true;
            }
        }

        boolean queueHasMessagesToBeSent = true;

        String messageToSend = msg;
        do {
            session.getBasicRemote().sendText(messageToSend);
            synchronized (this) {
                messageToSend = messageBacklog.poll();
                if (messageToSend == null) {
                    messageInProgress = false;
                    queueHasMessagesToBeSent = false;
                }
            }

        } while (queueHasMessagesToBeSent);
    }


    private static void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
                client.sendMessage(msg);
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                if (connections.remove(client)) {
                    try {
                        client.session.close();
                    } catch (IOException e1) {
                        // Ignore
                    }
                    String message = String.format("* %s %s", client.nickname, "has been disconnected.");
                    broadcast(message);
                }
            }
        }
    }
}
