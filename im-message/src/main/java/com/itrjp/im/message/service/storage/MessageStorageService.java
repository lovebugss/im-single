package com.itrjp.im.message.service.storage;

import com.itrjp.im.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 12:09
 */
@Slf4j
@Service
public class MessageStorageService {
    public void save(String from, String to, Message message, boolean filterResult) {
        log.info("保存消息, 过滤结果: {}", filterResult);

    }
}
