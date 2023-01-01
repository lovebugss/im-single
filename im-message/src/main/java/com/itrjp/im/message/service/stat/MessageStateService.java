package com.itrjp.im.message.service.stat;

import com.itrjp.im.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 12:07
 */
@Slf4j
@Service
public class MessageStateService {
    public void stat(String from, String to, Message parse, boolean filterResult) {
        log.info("统计消息, 过滤结果: {}", filterResult);
    }
}
