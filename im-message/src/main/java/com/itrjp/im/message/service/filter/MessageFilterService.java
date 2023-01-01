package com.itrjp.im.message.service.filter;

import com.itrjp.im.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:43
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageFilterService {
    private final MessageFilter[] messageFilters;

    public boolean filter(String channelId, Message message) {
        log.info("开始进行消息过滤");
        // 获取频道过滤器
        return MessageFilterFactory.create(getMessageFilterType(channelId)).filter(message);
        // 过滤消息
    }

    private MessageFilterType getMessageFilterType(String channelId) {
        return MessageFilterType.AUTO;
    }
}
