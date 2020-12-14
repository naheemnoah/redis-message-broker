package com.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.redis.demo.sender.RedisSender;

@RestController
public class RedisController {
	
	@Autowired
	RedisSender redisSender;
	
	@GetMapping("/redis/{message}")
	public void redisTest(@PathVariable String message) {
		redisSender.sendDataToRedisQueue(message);
	}

}
