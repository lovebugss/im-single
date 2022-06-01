package com.itrjp.im.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * WebSocketFrameToProtobufDecoder
 *
 * @author renjp
 * @date 2022/5/10 09:41
 */
@Slf4j
public class WebSocketFrameToProtobufDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        log.info("method: [decode], channelId: {}", ctx.channel().id().asLongText());

        ByteBuf byteBuf = msg.content();
        if (msg instanceof BinaryWebSocketFrame || msg instanceof TextWebSocketFrame) {
            // 二进制消息
            byteBuf.retain();
            out.add(msg.content());
            // pong
        } else if (msg instanceof CloseWebSocketFrame) {
            // close
            ctx.channel().close();
        }
    }
}
