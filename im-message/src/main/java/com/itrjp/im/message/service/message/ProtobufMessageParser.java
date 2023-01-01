package com.itrjp.im.message.service.message;

import com.google.protobuf.InvalidProtocolBufferException;
import com.itrjp.im.message.Message;
import com.itrjp.im.proto.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 17:50
 */
@Slf4j
@Service
public class ProtobufMessageParser implements MessageParser {

    @Override
    public Message parseMessage(byte[] data) {
        try {
            return Message.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.warn("消息解析失败", e);
            return null;
        }
    }

    @Override
    public Event parseEvent(byte[] data) {
        try {
            return Event.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.warn("事件解析失败", e);
            return null;
        }
    }
}
