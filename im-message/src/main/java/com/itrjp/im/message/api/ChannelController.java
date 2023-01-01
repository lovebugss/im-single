package com.itrjp.im.message.api;

import com.itrjp.im.common.service.TokenService;
import com.itrjp.im.message.pojo.param.InitParam;
import com.itrjp.im.message.pojo.vo.ChannelConnectInfo;
import com.itrjp.im.message.pojo.vo.ChannelNodeInfo;
import com.itrjp.im.message.service.channel.ChannelService;
import com.itrjp.im.message.service.stat.ConnectNodeLoad;
import com.itrjp.im.message.service.stat.ConnectNodeLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 14:58
 */
@RestController()
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ConnectNodeLoadService connectNodeLoadService;
    private final ChannelService channelService;
    private final TokenService tokenService;

    @GetMapping("{channelId}/init")
    public ResponseEntity<ChannelConnectInfo> init(@PathVariable("channelId") String channelId, InitParam param) {
        // 检查当前channelId是否存在
        channelService.checkChannelId(channelId);
        // 获取当前房间最佳节点
        List<ConnectNodeLoad> nodeInfo = connectNodeLoadService.getBestNode(channelId);
        // 当前时间戳
        long currentTime = System.currentTimeMillis() / 1000;
        // 生成token
        String token = tokenService.create(channelId, currentTime, param.getUserId());
        return ResponseEntity.ok(new ChannelConnectInfo(channelId, nodeInfo.stream().map(this::toChannelNodeInfo).toList(), token, currentTime));
    }

    private ChannelNodeInfo toChannelNodeInfo(ConnectNodeLoad connectNodeLoad) {
        return new ChannelNodeInfo(connectNodeLoad.getIp(), "ws", connectNodeLoad.getWsPort());
    }
}
