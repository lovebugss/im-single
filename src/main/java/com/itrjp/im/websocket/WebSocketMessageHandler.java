package com.itrjp.im.websocket;

import com.itrjp.im.proto.MessageProtobuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocketMessageHandler
 *
 * @author renjp
 * @date 2022/5/9 22:16
 */
@Slf4j
@ChannelHandler.Sharable
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<MessageProtobuf.Message> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.add(channel);
        super.handlerAdded(ctx);
    }

    //服务端监听到客户端不活动
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("exceptionCaught", cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtobuf.Message msg) throws Exception {
        Channel channel = ctx.channel();
        // 消息
        System.out.println(msg);
    }
}
