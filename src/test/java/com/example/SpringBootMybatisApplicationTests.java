package com.example;

import com.example.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class SpringBootMybatisApplicationTests extends Thread {




    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    DataSource dataSource;
    static int k = 0;

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("子线程" + i);
        }
    }

 /*   @Test
    void contextLoads() throws ClassNotFoundException {
        new Thread()


    }*/

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.100.129", 6379);
        jedis.select(0);
        String name = jedis.get("name");
        System.out.println(name);

    }

}
