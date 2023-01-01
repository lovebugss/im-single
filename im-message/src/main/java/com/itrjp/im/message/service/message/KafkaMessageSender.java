package com.itrjp.im.message.service.message;

import com.itrjp.im.common.conts.KafkaConstant;
import com.itrjp.im.common.util.TraceUtil;
import com.itrjp.im.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * kafka消息发送者
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 12:19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSender implements MessageSender {
    private final org.springframework.kafka.core.KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void send(String nodeId, String from, String to, Message data) {
        org.springframework.messaging.Message<byte[]> message = MessageBuilder.withPayload(data.toByteArray())
                .setHeader(KafkaHeaders.TOPIC, KafkaConstant.TOPIC_PREFIX + nodeId)
                .setHeader(KafkaConstant.HEADER_FROM, from)
                .setHeader(KafkaConstant.HEADER_TO, to)
                .setHeader(TraceUtil.HEADER_TRACE_ID, TraceUtil.getTraceId())
                .build();
        log.info("发送消息到kafka, from: {}, to: {}, data: {}", from, to, data);
        kafkaTemplate.send(message);
    }
}
