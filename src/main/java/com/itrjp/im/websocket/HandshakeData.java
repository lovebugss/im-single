package com.itrjp.im.websocket;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * HandshakeData
 *
 * @author renjp
 * @date 2022/5/9 18:19
 */
@Getter
@ToString
public class HandshakeData implements Serializable {
    private final LocalDateTime time = LocalDateTime.now();
    private Map<String, List<String>> param;
    private String url;

    public HandshakeData() {
    }

    public HandshakeData(Map<String, List<String>> urlParams, String url) {
        this.url = url;
        this.param = urlParams;
    }
}
