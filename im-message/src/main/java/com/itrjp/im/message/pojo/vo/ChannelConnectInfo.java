package com.itrjp.im.message.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelConnectInfo {
    private String channelId;
    private List<ChannelNodeInfo> nodeInfo;
    private String token;
    private long time;
}
