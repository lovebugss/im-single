package com.itrjp.im.message.service.stat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 14:19
 */
@Slf4j
@Service
public class ConnectNodeLoadServiceImpl implements ConnectNodeLoadService {
    /**
     * 可用的connect的节点列表
     */
    private final Map<String, ConnectNodeLoad> availableNodes = new ConcurrentHashMap<>(32);
    // 频道和节点映射
    private final Map<String, List<String>> channelNodeMap = new ConcurrentHashMap<>(32);

    private final PriorityQueue<ConnectNodeLoad> connectNodeLoads = new PriorityQueue<>();


    @Override
    public void start(ConnectNodeLoad connectNodeLoad) {
        log.info("connect node start: {}", connectNodeLoad);
        availableNodes.put(connectNodeLoad.getId(), connectNodeLoad);
        connectNodeLoads.add(connectNodeLoad);
        System.out.println(connectNodeLoads);
    }

    @Override
    public void connect(String nodeId, String channelId, String userId) {
        log.info("connect node connect: {}, {}, {}", nodeId, channelId, userId);
        ConnectNodeLoad connectNodeLoad = availableNodes.get(nodeId);
        List<String> connectNodeIds = channelNodeMap.get(channelId);
        if (connectNodeIds == null) {
            connectNodeIds = new ArrayList<>();
        }
        connectNodeIds.add(connectNodeLoad.getId());
        channelNodeMap.put(channelId, connectNodeIds);
        connectNodeLoad.setLoad(connectNodeLoad.getLoad() + 1);
        this.connectNodeLoads.remove(connectNodeLoad);
        this.connectNodeLoads.add(connectNodeLoad);

    }

    @Override
    public void disconnect(String nodeId, String channelId, String userId) {
        log.info("disconnect: {}, {}, {}", nodeId, channelId, userId);
        ConnectNodeLoad connectNodeLoad = availableNodes.get(nodeId);
        List<String> connectNodeIds = channelNodeMap.get(channelId);
        if (connectNodeIds == null) {
            connectNodeIds = new ArrayList<>();
        }
        connectNodeIds.remove(connectNodeLoad.getId());
        connectNodeLoad.setLoad(connectNodeLoad.getLoad() - 1);
        this.connectNodeLoads.add(connectNodeLoad);
    }

    @Override
    public void stop(ConnectNodeLoad connectNodeLoad) {
        log.info("connect node stop: {}", connectNodeLoad);
        availableNodes.remove(connectNodeLoad.getId());
        connectNodeLoads.remove(connectNodeLoad);
    }

    @Override
    public List<ConnectNodeLoad> getConnectNodeByChannelId(String channelId) {
        List<String> nodeIds = channelNodeMap.getOrDefault(channelId, Collections.emptyList());
        return nodeIds.stream()
                .distinct()
                .map(availableNodes::get)
                .toList();
    }

    @Override
    public List<ConnectNodeLoad> getBestNode(String channelId) {
        return this.connectNodeLoads.stream().limit(2).toList();
    }

    @Override
    public Collection<ConnectNodeLoad> getAvailableList() {
        return availableNodes.values();
    }
}
