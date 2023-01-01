package com.itrjp.im.connect.websocket.listener;

import com.itrjp.im.connect.websocket.WebSocketClient;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/20 18:59
 */
public interface MessageListener {

    void onMessage(WebSocketClient client, com.itrjp.im.message.Message data);
}
