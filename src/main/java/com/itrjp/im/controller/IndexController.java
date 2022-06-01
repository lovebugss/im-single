package com.itrjp.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/5/9 16:53
 */
@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
