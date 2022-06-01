package com.itrjp.im.service;

import com.itrjp.im.pojo.param.SendMessageParam;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.itrjp.im.constant.KafkaConst.TOPIC_MESSAGE;

/**
 * MessageService
 *
 * @author renjp
 * @date 2022/5/10 09:57
 */
@Service
public class MessageService {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public MessageService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(SendMessageParam param) {
        // TODO乱七八糟检查

        kafkaTemplate.send(TOPIC_MESSAGE, new byte[0]);
    }
}
