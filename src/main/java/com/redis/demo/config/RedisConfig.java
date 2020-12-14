package com.redis.demo.config;

import java.util.UUID;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.redis.demo.receiver.RedisReceiver;

@Configuration
//@EnableRedisRepositories
public class RedisConfig {
	
//	@Value("${spring.redis.port}") int redisPort; 
//    @Value("${spring.redis.host}") String redisHost;
	
//	@Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory("localhost", 6370);
//    }
//
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
//        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        return template;
//    }
    
    
	
	@Bean
	JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		return factory;
	}
	
	
	@Bean
	RedisTemplate<String, String> redisTemplate (){
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
		return template;
	}
	
	@Bean
	ChannelTopic topic() {
		return new ChannelTopic(UUID.randomUUID().toString());
	}
	
	
	@Bean
	RedisMessageListenerContainer redisContainer() {
	    RedisMessageListenerContainer container 
	      = new RedisMessageListenerContainer(); 
	    container.setConnectionFactory(connectionFactory()); 
	    container.addMessageListener(new MessageListenerAdapter(new RedisReceiver()), topic()); 
	    container.setTaskExecutor(Executors.newFixedThreadPool(4));
	    return container; 
	}
	

}
