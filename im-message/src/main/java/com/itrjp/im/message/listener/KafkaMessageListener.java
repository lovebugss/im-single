package com.itrjp.im.message.listener;

import com.itrjp.im.common.conts.KafkaConstant;
import com.itrjp.im.kafka.DataType;
import com.itrjp.im.kafka.KafkaMessage;
import com.itrjp.im.message.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 消息监听器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:25
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaMessageListener {

    private final MessageService messageService;

    @KafkaListener(topics = KafkaConstant.TOPIC_IM_MESSAGE)
    void onChannelMessage(byte[] data) {
        try {
            KafkaMessage kafkaMessage = KafkaMessage.parseFrom(data);
            DataType dataType = kafkaMessage.getDataType();
            switch (dataType) {
                case MESSAGE ->
                        messageService.handleMessage(kafkaMessage.getFrom(), kafkaMessage.getTo(), kafkaMessage.getData().toByteArray());
                case EVENT ->
                        messageService.handleEvent(kafkaMessage.getNodeId(), kafkaMessage.getData().toByteArray());
                default -> log.warn("未知的消息类型: {}", dataType);
            }

        } catch (Exception e) {
            log.error("消息处理失败", e);
        }
    }

}
