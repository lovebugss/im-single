package com.itrjp.im.websocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * WebSocket Server
 *
 * @author renjp
 * @date 2022/2/18 16:54
 */
@Slf4j
@Component
public class WebSocketServer {
    private final WebSocketProperties configuration;
    private SslContext sslCtx;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private WebSocketChannelInitializer webSocketChannelInitializer;

    public WebSocketServer(WebSocketProperties configuration) {
        this.configuration = configuration;
    }

    /**
     * 初始化
     *
     * @throws Exception
     */
    private void initializer() throws Exception {
        // group
        bossGroup = new NioEventLoopGroup(configuration.getBossThreads());
        workerGroup = new NioEventLoopGroup(configuration.getWorkThreads());
        // ssl
        if (configuration.isUseSSL()) {
            sslCtx = createSslContext(configuration);
        }
        webSocketChannelInitializer = new WebSocketChannelInitializer(configuration, sslCtx, new WebSocketMessageHandler());
    }

    /**
     * 创建ssl context
     *
     * @param configuration
     * @return
     * @throws Exception
     */
    private SslContext createSslContext(WebSocketProperties configuration) throws Exception {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
    }

    public void start() throws Exception {
        start((b) -> {
        });
    }

    /**
     * 开始
     *
     * @throws Exception
     */
    public void start(Consumer<Boolean> callback) throws Exception {
        initializer();
        startSync()
                .addListener(future -> {
                    boolean success = future.isSuccess();
                    if (success) {
                        log.info("WebSocket server started at port: {}", configuration.getPort());

                    } else {
                        log.warn("WebSocket server startup failed", future.cause());
                    }
                    callback.accept(success);
                })
                .syncUninterruptibly();
    }

    private Future<Void> startSync() {

        ServerBootstrap bootstrap = new ServerBootstrap();
        addOption(bootstrap);

        Class<? extends ServerChannel> channelClass = NioServerSocketChannel.class;
        bootstrap.group(bossGroup, workerGroup)
                .channel(channelClass)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(webSocketChannelInitializer);

        return bootstrap.bind(configuration.getPort());
    }

    private void addOption(ServerBootstrap bootstrap) {
        bootstrap.option(ChannelOption.SO_BACKLOG, configuration.getTcp().getBacklog());
    }

    public void stop() {
        log.info("stop server...");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}

