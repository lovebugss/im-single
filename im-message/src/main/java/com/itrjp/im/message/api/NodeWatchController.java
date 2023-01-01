package com.itrjp.im.message.api;

import com.itrjp.im.message.pojo.consul.ConsulWatchServiceEntity;
import com.itrjp.im.message.service.stat.NodeWatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/26 14:48
 */
@Slf4j
@RestController
@RequestMapping("consul")
@RequiredArgsConstructor
public class NodeWatchController {

    private final NodeWatchService nodeWatchService;


    @PostMapping("/watch/service")
    public String service(@RequestBody List<ConsulWatchServiceEntity> entities) {
        log.info("event: service, param: {}", entities);
        nodeWatchService.handlerServiceNotify(entities);
        return "success";
    }

}
