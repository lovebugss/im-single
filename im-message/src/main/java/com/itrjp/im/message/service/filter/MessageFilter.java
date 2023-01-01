package com.itrjp.im.message.service.filter;

import com.itrjp.im.message.Message;

/**
 * 消息过滤器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:22
 */
public interface MessageFilter {
    /**
     * 过滤消息
     *
     * @param message
     * @return
     */
    boolean filter(Message message);
}
