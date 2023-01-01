package com.itrjp.im.message.service.message;

import com.itrjp.im.message.Message;
import com.itrjp.im.message.service.filter.MessageFilterService;
import com.itrjp.im.message.service.stat.ConnectNodeLoad;
import com.itrjp.im.message.service.stat.ConnectNodeLoadService;
import com.itrjp.im.message.service.stat.MessageStateService;
import com.itrjp.im.message.service.storage.MessageStorageService;
import com.itrjp.im.proto.Event;
import com.itrjp.im.proto.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 11:43
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageParser messageParser;
    private final MessageFilterService messageFilterService;

    private final MessageStateService messageStateService;

    private final MessageStorageService messageStorageService;

    private final MessageSender messageSender;
    private final ConnectNodeLoadService connectNodeLoadService;


    public void handleMessage(String from, String to, byte[] data) {
        log.info("收到消息: from: {}, to: {}, data: {}", from, to, data);
        // 解析消息体
        Message message = messageParser.parseMessage(data);
        if (message == null) {
            log.warn("消息解析失败");
            return;
        }
        // 消息过滤
        boolean filter = messageFilterService.filter(to, message);
        // 消息分发
        if (filter) {
            // 发送到connect节点, 进行广播
            sendToConnectNode(from, to, message);
        }
        // 消息存储
        messageStorageService.save(from, to, message, filter);
        // 消息统计
        messageStateService.stat(from, to, message, filter);
    }

    private void sendToConnectNode(String from, String to, Message message) {
        log.info("发送消息到connect节点, from: {}, to: {}, message: {}", from, to, message);
        // 找到当前频道所在的connect节点
        List<ConnectNodeLoad> connectNodeLoads = getConnectNodeByChannelId(to);
        // 发送消息
        for (ConnectNodeLoad connectNodeLoad : connectNodeLoads) {
            log.info("发送消息到connect节点: {}", connectNodeLoad.getId());
            messageSender.send(connectNodeLoad.getId(), from, to, message);
        }
    }

    private List<ConnectNodeLoad> getConnectNodeByChannelId(String to) {
        return connectNodeLoadService.getConnectNodeByChannelId(to);
    }

    public void handleEvent(String nodeId, byte[] data) {
        log.info("收到事件: nodeId: {}, data: {}", nodeId, data);
        Event event = messageParser.parseEvent(data);
        if (event == null) {
            return;
        }
        eventHandler(nodeId, event);
    }

    private void eventHandler(String nodeId, Event event) {
        EventType type = event.getType();
        String userId = event.getUserId();
        String channelId = event.getChannelId();
        switch (type) {
            case ONLINE -> connectNodeLoadService.connect(nodeId, channelId, userId);
            case OFFLINE -> connectNodeLoadService.disconnect(nodeId, channelId, userId);
            default -> log.warn("未知事件类型: {}", type);
        }
    }
}
