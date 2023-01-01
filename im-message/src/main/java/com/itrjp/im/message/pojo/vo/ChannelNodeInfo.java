package com.itrjp.im.message.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/31 15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelNodeInfo {
    private String address;
    private String protocol;
    private int port;
}
