package com.itrjp.im.connect.websocket.channel;

import com.itrjp.im.connect.websocket.WebSocketClient;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/21 19:09
 */
public class WebsocketChannel {
    private final String channelId;
    private final Map<String, WebSocketClient> clients = new ConcurrentHashMap<>(16);


    /**
     * 加入房间
     *
     * @param client
     */
    public void join(WebSocketClient client) {
        clients.put(client.getSession(), client);
    }

    /**
     * 离开房间
     *
     * @param client
     */
    public void leave(WebSocketClient client) {
        clients.remove(client.getSession());
    }

    public Collection<WebSocketClient> getAllClient() {
        return clients.values();
    }

    public WebsocketChannel(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public WebSocketClient getClient(String sessionId) {
        return clients.get(sessionId);
    }
}
