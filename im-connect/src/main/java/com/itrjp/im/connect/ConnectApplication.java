package com.itrjp.im.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:02
 */
@SpringBootApplication(scanBasePackages = "com.itrjp")
@EnableAutoConfiguration
@EnableDiscoveryClient
public class ConnectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }
}
