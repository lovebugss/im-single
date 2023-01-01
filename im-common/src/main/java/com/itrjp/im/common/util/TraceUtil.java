package com.itrjp.im.common.util;

import org.slf4j.MDC;

/**
 * Trace Id 工具类
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:31
 */
public class TraceUtil {
    public static final String HEADER_TRACE_ID = "X-Request-Id";
    public static final String TRACE_ID = "traceId";

    public static void addTraceId() {
        String traceId = getTraceId();
        if (traceId == null) {
            traceId = createTraceId();
        }
        MDC.put(TRACE_ID, traceId);
    }

    public static String createTraceId() {
        return null;
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void clear() {
        MDC.remove(TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }
}
