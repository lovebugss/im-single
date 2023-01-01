package com.itrjp.im.message.service.filter;

import com.itrjp.im.message.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:38
 */
@Slf4j
public class AutoMessageFilter implements MessageFilter {
    @Override
    public boolean filter(Message message) {
        log.info("自动过滤器过滤消息");
        return true;
    }
}
