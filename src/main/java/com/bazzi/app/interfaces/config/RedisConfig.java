package com.bazzi.app.interfaces.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    
    @Value("${spring.data.redis.host}")
    private String host;
    
    @Value("${spring.data.redis.port}")
    private int port;
    
    @Value("${spring.data.redis.password}")
    private String password;
    
    @Value("${spring.data.redis.ssl.enabled:false}")
    private boolean sslEnabled;
    
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Redis 연결 설정
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(host);
        redisConfig.setPort(port);
        redisConfig.setUsername("default");  // Upstash는 반드시 default 사용
        redisConfig.setPassword(password);
        
        // Lettuce 클라이언트 설정
        LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfig = 
            LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(10));
        
        // SSL 설정
        if (sslEnabled) {
            clientConfig.useSsl();
        }
        
        return new LettuceConnectionFactory(redisConfig, clientConfig.build());
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        
        // String Serializer 설정
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        
        template.afterPropertiesSet();
        return template;
    }
}