package org.data.traffic_01.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import java.time.Duration;

@Configuration
public class RedisConfig {

    // Redis 관련 설정을 정의하는 Spring Bean
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().build();
//        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("localhost", 6379);
//        return new JedisConnectionFactory(serverConfig, clientConfig);
//    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");
        config.setPort(6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // Redis와 데이터를 주고받기 위한 RedisTemplate 설정
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory()); // Redis 연결 팩토리 설정
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // Key를 문자열로 직렬화
        redisTemplate.setValueSerializer(new StringRedisSerializer()); // Value를 문자열로 직렬화
        return redisTemplate;
    }

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        // Redis의 자동 구성을 방지하기 위한 설정
        // NO_OP: Redis 클러스터 또는 Sentinel 환경에서 기본 설정을 적용하지 않도록 방지
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public CacheManager cacheManager() {
        // RedisCacheManager 빌더 생성
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());

        // 캐시 설정 구성
        RedisCacheConfiguration configuration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                        )
                        .entryTtl(Duration.ofMinutes(5)); // 캐시 만료 시간 설정

        // 빌더에 기본 캐시 설정 적용
        builder.cacheDefaults(configuration);

        // CacheManager 빌드 및 반환
        return builder.build();
    }

}
