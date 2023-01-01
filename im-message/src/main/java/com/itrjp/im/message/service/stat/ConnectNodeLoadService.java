package com.itrjp.im.message.service.stat;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * connect 节点负载
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 09:31
 */
@Service
public interface ConnectNodeLoadService {
    /**
     * Connect 节点启动
     */
    void start(ConnectNodeLoad connectNodeLoad);

    /**
     * Connect 节点关闭
     *
     * @param connectNodeLoad
     */
    void stop(ConnectNodeLoad connectNodeLoad);

    void connect(String nodeId, String channelId, String userId);

    void disconnect(String nodeId, String channelId, String userId);

    /**
     * 获取频道分布的connect节点
     *
     * @param channelId
     * @return
     */
    List<ConnectNodeLoad> getConnectNodeByChannelId(String channelId);

    List<ConnectNodeLoad> getBestNode(String channelId);

    Collection<ConnectNodeLoad> getAvailableList();

}
