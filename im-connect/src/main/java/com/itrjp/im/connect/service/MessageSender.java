package com.itrjp.im.connect.service;

import com.itrjp.im.proto.Event;

/**
 * 消息发送者
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 10:25
 */
public interface MessageSender {

    void sendMessage(String from, String to, com.itrjp.im.message.Message message);

    void sendEvent(String instanceId, Event event);
}
