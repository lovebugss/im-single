package com.itrjp.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 程序主入口
 *
 * @author renjp
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class IMApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMApplication.class, args);
    }

}
