//package com.redis.demo.config;
//
//import java.io.IOException;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//import org.springframework.stereotype.Component;
//
//import redis.embedded.RedisServer;
//
//
//@Component
//public class TestRedisConfiguration {
//	
//	
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void startRedis() throws IOException {
//        redisServer = new RedisServer(6372);
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        this.redisServer.stop();
//    }
//}
