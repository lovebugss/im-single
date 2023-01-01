package com.itrjp.im.message.service.message;

import com.itrjp.im.message.Message;

/**
 * 消息发送者
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 12:16
 */
public interface MessageSender {
    void send(String nodeId, String from, String to, Message parse);
}
