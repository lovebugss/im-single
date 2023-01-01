package com.itrjp.im.connect.listener;

import com.itrjp.im.common.util.TraceUtil;
import com.itrjp.im.connect.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.itrjp.im.common.conts.KafkaConstant.HEADER_FROM;
import static com.itrjp.im.common.conts.KafkaConstant.HEADER_TO;

/**
 * kafka消息监听器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 09:55
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final MessageService messageService;

    @KafkaListener(topics = {"${im.connect-topic}"})
    public void onMessage(byte[] data,
//                          @Header(TraceUtil.HEADER_TRACE_ID) String traceId,
                          @Header(HEADER_FROM) String from,
                          @Header(HEADER_TO) String to) {
        try {
//            if (traceId != null) {
//                TraceUtil.setTraceId(traceId);
//            }
            log.info("接受Kafka消息: {}, from: {}, to: {}", data.length, from, to);
            messageService.handlerMessage(from, to, data);
        } catch (Exception e) {
            log.error("消息处理失败", e);
        } finally {
//            TraceUtil.clear();
        }
    }

    @KafkaListener(topics = {"${im.connect-notify-topic}"})
    public void onEvent(byte[] data,
//                        @Header(TraceUtil.HEADER_TRACE_ID) String traceId,
                        @Header(HEADER_FROM) String from,
                        @Header(HEADER_TO) String to) {
        try {
//            if (traceId != null) {
//                TraceUtil.setTraceId(traceId);
//            }
            log.info("接受Kafka消息: {}, from: {}, to: {}", data.length, from, to);
            messageService.handlerEvent(from, to, data);
        } catch (Exception e) {
            log.error("消息处理失败", e);
        } finally {
//            TraceUtil.clear();
        }
    }

}
