package com.itrjp.im.controller;

import com.itrjp.im.pojo.param.InitParam;
import com.itrjp.im.result.Result;
import com.itrjp.im.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ChatController
 *
 * @author renjp
 * @date 2022/5/31 15:19
 */
@RestController
@RequestMapping("chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("init")
    public Result<Void> init(InitParam param) {

        // 获取连接节点

        // 生成token

        // 响应
        chatService.init(param.getChannelId(), param.getUserId());
        return Result.success();
    }

}
