package com.itrjp.im.connect.service;

import com.itrjp.im.common.conts.KafkaConstant;
import com.itrjp.im.kafka.DataType;
import com.itrjp.im.kafka.KafkaMessage;
import com.itrjp.im.proto.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * kafka
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 10:33
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSender implements MessageSender {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void sendMessage(String from, String to, com.itrjp.im.message.Message data) {

        log.info("发送Kafka消息: {}, from: {}, to: {}", data, from, to);
        KafkaMessage message = KafkaMessage.newBuilder()
                .setDataType(DataType.MESSAGE)
                .setTimestamp(System.currentTimeMillis())
                .setData(data.toByteString())
                .setFrom(from)
                .setTo(to)
                .build();
        kafkaTemplate.send(KafkaConstant.TOPIC_IM_MESSAGE, message.toByteArray());
    }

    @Override
    public void sendEvent(String instanceId, Event event) {
        KafkaMessage message = KafkaMessage.newBuilder()
                .setDataType(DataType.MESSAGE)
                .setTimestamp(System.currentTimeMillis())
                .setData(event.toByteString())
                .setNodeId(instanceId)
                .build();
        log.info("发送Kafka事件: {}, instanceId: {}", event, instanceId);
        kafkaTemplate.send(KafkaConstant.TOPIC_IM_MESSAGE, message.toByteArray());
    }
}
