package com.itrjp.im.websocket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author renjp
 * @date 2022/5/9 18:19
 */
@ConfigurationProperties("im")
@Getter
@Setter
public class WebSocketProperties {
    private int bossThreads;
    private int workThreads;

    private int maxFramePayloadLength = 5 * 1024 * 1024;
    /**
     * host
     */
    @Value("${server.address:localhost}")
    private String host;
    /**
     * port
     */
    private int port = 18080;

    private String topic;

    private Tcp tcp = new Tcp();
    private boolean useSSL;

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public int getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(int bossThreads) {
        this.bossThreads = bossThreads;
    }

    public int getWorkThreads() {
        return workThreads;
    }

    public void setWorkThreads(int workThreads) {
        this.workThreads = workThreads;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Tcp getTcp() {
        return tcp;
    }

    public void setTcp(Tcp tcp) {
        this.tcp = tcp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static class Tcp {
        private int backlog = 1024;

        public int getBacklog() {
            return backlog;
        }

        public void setBacklog(int backlog) {
            this.backlog = backlog;
        }
    }
}
