package com.example;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class father implements testsd {

    static {
        System.out.println("我是father");
    }


    @Override
    public void testMeth() {
        System.out.println("father test");
    }
}
