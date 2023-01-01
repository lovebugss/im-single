package com.itrjp.im.message.service.stat;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class ConnectNodeLoadServiceImplTest {
    ConnectNodeLoadService connectNodeLoadService = new ConnectNodeLoadServiceImpl();


    @Test
    void test() {
        start();
        System.out.println("---1----");
        connect();
        System.out.println("---2----");
        getAvailableList();
        System.out.println("---3----");
        getConnectNodeByChannelId();
        System.out.println("---4----");
        getBestNode();
        System.out.println("---5----");
        getAvailableList();
        System.out.println("---6----");
    }

    void start() {
        connectNodeLoadService.start(new ConnectNodeLoad("1", "127.0.0.1", 8080, 18080, 0));
        connectNodeLoadService.start(new ConnectNodeLoad("2", "127.0.0.1", 8081, 18081, 0));
        connectNodeLoadService.start(new ConnectNodeLoad("3", "127.0.0.1", 8082, 18082, 0));
    }

    void connect() {
        connectNodeLoadService.connect("1", "1", "111");
        connectNodeLoadService.connect("1", "1", "123");
        connectNodeLoadService.connect("2", "1", "222");
    }
//
//    @Test
//    void disconnect() {
//    }


    void getConnectNodeByChannelId() {
        List<ConnectNodeLoad> connectNodeByChannelId = connectNodeLoadService.getConnectNodeByChannelId("1");
        System.out.println(connectNodeByChannelId);
    }

    void getBestNode() {
        List<ConnectNodeLoad> bestNode = connectNodeLoadService.getBestNode("1");
        System.out.println(bestNode);
    }

    void getAvailableList() {
        Collection<ConnectNodeLoad> availableList = connectNodeLoadService.getAvailableList();
        System.out.println(availableList);
    }

    void stop() {
        connectNodeLoadService.stop(new ConnectNodeLoad("1", "127.0.0.1", 8080, 18080, 0));
        connectNodeLoadService.stop(new ConnectNodeLoad("2", "127.0.0.1", 8081, 18081, 0));
        connectNodeLoadService.stop(new ConnectNodeLoad("3", "127.0.0.1", 8082, 18082, 0));
    }

}