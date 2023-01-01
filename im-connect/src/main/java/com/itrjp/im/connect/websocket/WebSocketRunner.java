package com.itrjp.im.connect.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * websocket启动器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/24 11:17
 */
@Component
@RequiredArgsConstructor
public class WebSocketRunner implements CommandLineRunner, DisposableBean {
    private final WebSocketServer server;
    private final WebSocketProperties webSocketProperties;


    @Override
    public void run(String... args) throws Exception {
        // 启动Websocket
        server.start();
        // 注册当前服务
        int port = webSocketProperties.getPort();
        String host = webSocketProperties.getHost();
    }

    @Override
    public void destroy() throws Exception {
        server.stop();
    }
}
