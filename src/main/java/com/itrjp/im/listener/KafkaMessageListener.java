package com.itrjp.im.listener;

import com.itrjp.im.proto.Kafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Kafka监听器
 *
 * @author renjp
 * @date 2022/5/10 10:20
 */

@Slf4j
@Component
public class KafkaMessageListener {

    //    @KafkaListener(topics = KafkaConst.TOPIC_MESSAGE)
    public void messageListener(Kafka.KafkaMessage message) {
        log.info("接受到kafka消息: {}", message.toString());
    }
}
