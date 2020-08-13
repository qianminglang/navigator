package com.clear.navigator.service;

import com.clear.navigator.dto.TextEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ClassName WebSocket
 *
 * @author qml
 * Date 2020/8/11 9:46
 * Version 1.0
 **/
@Component
@ServerEndpoint(value = "/websocket",encoders = {TextEncoder.class})
@Slf4j
public class WebSocket {
    private Session session;

    private static final CopyOnWriteArraySet<WebSocket> websocketSet = new CopyOnWriteArraySet<>();

    public WebSocket() {
        log.info("WebSocket初始化");
    }

    @OnOpen
    public void open(Session session) {
        this.session = session;
        websocketSet.add(this);
        log.info("【websocket消息】有新的连接，总数：{}", websocketSet.size());
    }

    @OnClose
    public void onClose() {
        websocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数：{}", websocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息：{}", message);
    }

    public void sendMessage(Object message) {
        for (WebSocket webSocket : websocketSet) {
            log.info("【websocket消息】广播消息：message={}", message);
            try {
                webSocket.session.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}