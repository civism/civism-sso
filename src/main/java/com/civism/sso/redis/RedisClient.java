package com.civism.sso.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Component
public class RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     *
     * @param timeOut 超时时间 单位秒
     */
    public void set(String key, Object value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 永不过期（相对意义）
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 删除
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     *
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 刷新key实效
     * @param key
     * @param time
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

}
