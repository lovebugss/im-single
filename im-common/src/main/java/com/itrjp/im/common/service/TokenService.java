package com.itrjp.im.common.service;

/**
 * TokenService
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/11/16 08:23
 */
public interface TokenService {
    /**
     * 检查token 是否合法
     *
     * @param token
     * @return
     */
    boolean check(String token, String channelId, long currentTime, String uid);

    /**
     * 创建token
     *
     * @return
     */
    String create(String channelId, long currentTime, String uid);

    boolean checkTime(long time);
}
