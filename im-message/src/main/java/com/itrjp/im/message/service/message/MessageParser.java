package com.itrjp.im.message.service.message;

import com.itrjp.im.message.Message;
import com.itrjp.im.proto.Event;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 11:46
 */
public interface MessageParser {
    Message parseMessage(byte[] data);

    Event parseEvent(byte[] data);
}
