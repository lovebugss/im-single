package com.itrjp.im.connect.websocket.handler;

import com.itrjp.im.connect.websocket.WebSocketClient;
import com.itrjp.im.connect.websocket.channel.ChannelsHub;
import com.itrjp.im.connect.websocket.listener.CloseListener;
import com.itrjp.im.connect.websocket.listener.ExceptionListener;
import com.itrjp.im.connect.websocket.listener.MessageListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MessageHandler
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/11 17:58
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<com.itrjp.im.message.Message> {
    private final MessageListener messageListener;
    private final ExceptionListener exceptionListener;
    private final CloseListener closeListener;
    private final ChannelsHub channelsHub;


    public MessageHandler(MessageListener messageListener, ExceptionListener exceptionListener, CloseListener closeListener, ChannelsHub channelsHub) {
        this.messageListener = messageListener;
        this.exceptionListener = exceptionListener;
        this.closeListener = closeListener;
        this.channelsHub = channelsHub;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, com.itrjp.im.message.Message msg) throws Exception {
        messageListener.onMessage(channelsHub.getWebSocketClient(ctx.channel().id().asLongText()), msg);
    }

    /**
     * 异常
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("exceptionCaught", cause);
        exceptionListener.onException(channelsHub.getWebSocketClient(ctx.channel().id().asLongText()), cause);
        ctx.close();
    }

    //断开长链接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        WebSocketClient webSocketClient = channelsHub.getWebSocketClient(channel.id().asLongText());
        closeListener.onClose(webSocketClient);
        channelsHub.destroy(channel.id().asLongText());
        webSocketClient.leave();
        super.channelInactive(ctx);
    }

}
