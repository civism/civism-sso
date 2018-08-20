package com.civism.dao;

import java.util.Set;

/**
 * @author star
 * @date 2018/3/19 下午2:11
 */
public interface RedisClient {


    byte[] get(String key);


    boolean set(String key, byte[] value);


    boolean set(String key, byte[] value, long seconds);

    Set<byte[]> keys(String pattern);

    void remove(String key);

    boolean expire(String key, long seconds);

    long ttl(String key);
}
