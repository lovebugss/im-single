package com.itrjp.im.pojo.param;

import lombok.Data;

/**
 * SendMessageParam
 *
 * @author renjp
 * @date 2022/5/10 09:54
 */
@Data
public class SendMessageParam {
    /**
     * 发送时间
     */
    private long time;
    /**
     * 消息类型
     */
    private int type;
    /**
     * 消息体
     */
    private String content;
    /**
     * 发送人
     */
    private String from;
    /**
     * 接收人
     */
    private String to;
}
