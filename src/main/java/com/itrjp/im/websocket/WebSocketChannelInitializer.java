package com.itrjp.im.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * WebSocketChannelInitializer
 *
 * @author renjp
 * @date 2022/5/9 18:19
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;
    private final WebSocketServerHandler webSocketServerHandler;


    public WebSocketChannelInitializer(WebSocketProperties webSocketProperties, SslContext sslCtx, WebSocketMessageHandler webSocketMessageHandler) {
        this.sslCtx = sslCtx;
        webSocketServerHandler = new WebSocketServerHandler(webSocketProperties, webSocketMessageHandler);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast("http-server-codec", new HttpServerCodec());
        pipeline.addLast("http-object-aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("websocket-server-handler", webSocketServerHandler);

    }
}
