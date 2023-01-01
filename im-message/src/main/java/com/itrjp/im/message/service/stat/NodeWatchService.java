package com.itrjp.im.message.service.stat;

import com.itrjp.im.message.pojo.consul.Checks;
import com.itrjp.im.message.pojo.consul.ConsulWatchServiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 15:23
 */
@Service
@RequiredArgsConstructor
public class NodeWatchService {
    private final ConnectNodeLoadService connectNodeLoadService;

    public void handlerServiceNotify(List<ConsulWatchServiceEntity> param) {
        // 获取可用列表
        Collection<ConnectNodeLoad> availableList = connectNodeLoadService.getAvailableList();
        // 解析最近列表
        List<ConnectNodeLoad> connectNodes = resolveServiceNotify(param);
        // 查找下线服务
        List<ConnectNodeLoad> offlineList = findOffline(availableList, connectNodes);
        // 查找上线服务
        List<ConnectNodeLoad> onlineList = findOnline(availableList, connectNodes);
        offlineList.forEach(connectNodeLoadService::stop);
        onlineList.forEach(connectNodeLoadService::start);
    }

    /**
     * 查找上线服务
     *
     * @param availableList
     * @param connectNodes
     * @return
     */
    private List<ConnectNodeLoad> findOnline(Collection<ConnectNodeLoad> availableList, List<ConnectNodeLoad> connectNodes) {
        ArrayList<ConnectNodeLoad> result = new ArrayList<>(connectNodes);
        result.removeAll(availableList);
        return result;
    }

    /**
     * 查找上线服务
     *
     * @param availableList 可用列表
     * @param connectNodes  最新列表
     * @return
     */
    private List<ConnectNodeLoad> findOffline(Collection<ConnectNodeLoad> availableList, List<ConnectNodeLoad> connectNodes) {
        // 查找connectNodes 中不存在的数据
        ArrayList<ConnectNodeLoad> result = new ArrayList<>(availableList);
        result.removeAll(connectNodes);
        return result;
    }

    private List<ConnectNodeLoad> resolveServiceNotify(List<ConsulWatchServiceEntity> param) {
        if (param == null || param.isEmpty()) {
            return new ArrayList<>();
        }
        return param.stream()
                .filter((entity -> {
                    List<Checks> checks = entity.getChecks();
                    if (checks == null || checks.isEmpty()) {
                        return false;
                    }
                    for (Checks check : checks) {
                        if (check.getStatus().equals("passing")) {
                            return true;
                        }
                    }
                    return false;
                }))
                .map(entity -> {
                    com.itrjp.im.message.pojo.consul.Service service = entity.getService();
                    String address = service.getAddress();
                    String wsPort = service.getMeta().get("wsPort").toString();
                    int port = service.getPort();
                    String id = service.getId();
                    return new ConnectNodeLoad(id, address, port, Integer.parseInt(wsPort), 0);
                }).toList();
    }
}
