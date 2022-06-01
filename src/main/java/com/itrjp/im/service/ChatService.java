package com.itrjp.im.service;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/5/31 15:21
 */
@Service
public class ChatService {

    private final TokenService tokenService;

    public ChatService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void init(String channelId, String userId) {

        // 创建token
        String token = tokenService.create(channelId);
        
    }
}
