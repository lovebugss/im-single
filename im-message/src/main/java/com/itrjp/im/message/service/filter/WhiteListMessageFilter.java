package com.itrjp.im.message.service.filter;

import com.itrjp.im.message.Message;

/**
 * 白词过滤器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:40
 */
public class WhiteListMessageFilter implements MessageFilter {
    @Override
    public boolean filter(Message message) {
        return false;
    }
}
