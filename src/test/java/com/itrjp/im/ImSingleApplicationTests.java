package com.itrjp.im;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ImSingleApplicationTests {

    @Test
    void contextLoads() {
        List list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        List list2 = new ArrayList<>();
        list2.add("2");
        list2.add("4");
        list2.add("6");
        list1.retainAll(list2);

        System.out.println(list1);

    }

}
