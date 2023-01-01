package com.itrjp.im.connect.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.itrjp.im.connect.websocket.WebSocketClient;
import com.itrjp.im.connect.websocket.channel.ChannelsHub;
import com.itrjp.im.connect.websocket.channel.WebsocketChannel;
import com.itrjp.im.connect.websocket.listener.CloseListener;
import com.itrjp.im.connect.websocket.listener.MessageListener;
import com.itrjp.im.connect.websocket.listener.OpenListener;
import com.itrjp.im.message.Message;
import com.itrjp.im.proto.DataType;
import com.itrjp.im.proto.Event;
import com.itrjp.im.proto.EventType;
import com.itrjp.im.proto.Packet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 消息服务
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 09:57
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService implements MessageListener, OpenListener, CloseListener {

    private final MessageSender messageSender;
    private final ChannelsHub channelsHub;
    @Value("${spring.application.instance_id}")
    private String instanceId;

    /**
     * 处理来自Kafka中的消息
     *
     * @param from 发送者
     * @param to   接收者
     * @param data 消息内容
     */
    public void handlerMessage(String from, String to, byte[] data) {
        log.info("处理消息: from: {}, to: {}, data: {}", from, to, data.length);
        try {
            Message message = Message.parseFrom(data);
            broadcast(to, Packet.newBuilder()
                    .setMessage(message)
                    .setDataType(DataType.MESSAGE)
                    .setTimestamp(System.currentTimeMillis())
                    .build());
        } catch (InvalidProtocolBufferException e) {
            log.error("消息解析失败", e);
        }
    }

    private void broadcast(String channelId, Packet packet) {
        WebsocketChannel websocketChannel = channelsHub.get(channelId);
        if (websocketChannel == null) {
            return;
        }
        // 循环广播
        for (WebSocketClient webSocketClient : websocketChannel.getAllClient()) {
            webSocketClient.sendMessage(packet);
        }
    }

    @Override
    public void onMessage(WebSocketClient client, com.itrjp.im.message.Message message) {
        Map<String, List<String>> parameters = client.getParameters();
        String uid = parameters.get("uid").get(0);
        messageSender.sendMessage(uid, client.getChannelId(), message);
    }

    public void handlerEvent(String from, String to, byte[] data) {
        log.info("处理事件: from: {}, to: {}, data: {}", from, to, data.length);
    }

    @Override
    public void onClose(WebSocketClient client) {
        log.info("客户端关闭: {}", client.getSession());
        Map<String, List<String>> parameters = client.getParameters();
        String uid = parameters.get("uid").get(0);
        Event event = Event.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setType(EventType.OFFLINE)
                .setChannelId(client.getChannelId())
                .setUserId(uid)
                .build();
        messageSender.sendEvent(instanceId, event);
    }

    @Override
    public void onOpen(WebSocketClient client) {
        log.info("客户端连接: {}", client.getSession());
        Map<String, List<String>> parameters = client.getParameters();
        String uid = parameters.get("uid").get(0);
        Event event = Event.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setType(EventType.ONLINE)
                .setChannelId(client.getChannelId())
                .setUserId(uid)
                .build();
        messageSender.sendEvent(instanceId, event);
    }
}
