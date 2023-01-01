package com.itrjp.im.message.service.filter;

import com.itrjp.im.message.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * 人工审核
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:39
 */
@Slf4j
public class ManualMessageFilter implements MessageFilter {

    @Override
    public boolean filter(Message message) {
        log.info("人工审核过滤器过滤消息");
        return true;
    }
}
