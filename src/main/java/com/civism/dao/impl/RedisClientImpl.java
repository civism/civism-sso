package com.civism.dao.impl;

import com.civism.constants.SsoConstants;
import com.civism.dao.RedisClient;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * @author star
 * @date 2018/3/19 下午2:13
 */
public class RedisClientImpl implements RedisClient {


    private StringRedisTemplate redisTemplate;


    @Override
    public Set<byte[]> keys(String key) {
        return redisTemplate.execute((RedisConnection connection) ->
                connection.keys(SsoConstants.REDIS_KEY.concat("*").getBytes())
        );
    }


    @Override
    public byte[] get(String key) {

        return redisTemplate.execute((RedisConnection connection) ->
                connection.get(redisTemplate.getStringSerializer().serialize(key))
        );
    }

    @Override
    public boolean set(String key, byte[] value) {
        return redisTemplate.execute((RedisConnection connection) -> {
            connection.set(redisTemplate.getStringSerializer().serialize(key), value);
            return true;
        });
    }

    @Override
    public boolean set(String key, byte[] value, long seconds) {
        return redisTemplate.execute((RedisConnection connection) -> {
            connection.setEx(
                    redisTemplate.getStringSerializer().serialize(key), seconds,
                    value
            );
            return true;
        });
    }


    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long seconds) {
        return redisTemplate.execute((RedisConnection connection) ->
                connection.expire(redisTemplate.getStringSerializer().serialize(key), seconds)
        );
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.execute((RedisConnection connection) ->
                connection.ttl(redisTemplate.getStringSerializer().serialize(key))
        );
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
