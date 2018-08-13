package com.example.demo;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;


@Configuration
@EnableCaching//���û���
public class RedisConfig extends CachingConfigurerSupport{
  
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
    	 RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // ����һ��Ĭ�����ã�ͨ��config���󼴿ɶԻ�������Զ�������
    	 config = config.entryTtl(Duration.ofMinutes(1))     // ���û����Ĭ�Ϲ���ʱ�䣬Ҳ��ʹ��Duration����
    	            .disableCachingNullValues();     // �������ֵ

    	    // ����һ����ʼ���Ļ���ռ�set����
    	    Set<String> cacheNames =  new HashSet<>();
    	    cacheNames.add("my-redis-cache1");
    	    cacheNames.add("my-redis-cache2");

    	    // ��ÿ������ռ�Ӧ�ò�ͬ������
    	    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
    	    configMap.put("my-redis-cache1", config);
    	    configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(120)));

    	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // ʹ���Զ���Ļ������ó�ʼ��һ��cacheManager
    	            .initialCacheNames(cacheNames)  								// ע��������ĵ���˳��һ��Ҫ�ȵ��ø÷������ó�ʼ���Ļ��������ٳ�ʼ����ص�����
    	            .withInitialCacheConfigurations(configMap)
    	            .build();
    	    return cacheManager;
    }

}
