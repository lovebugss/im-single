package com.itrjp.im.controller;

import com.itrjp.im.pojo.param.SendMessageParam;
import com.itrjp.im.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/5/10 09:53
 */
@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("send")
    public void send(SendMessageParam param) {
        messageService.send(param);
    }
}
