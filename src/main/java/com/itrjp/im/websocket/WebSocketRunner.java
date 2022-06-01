package com.itrjp.im.websocket;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * WebSocketRunner
 *
 * @author renjp
 * @date 2022/5/9 21:16
 */
@Component
public class WebSocketRunner implements CommandLineRunner, DisposableBean {
    private final WebSocketProperties webSocketProperties;
    private final WebSocketServer server;

    public WebSocketRunner(WebSocketProperties webSocketProperties, WebSocketServer server) {
        this.webSocketProperties = webSocketProperties;
        this.server = server;
    }

    @Override
    public void destroy() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
