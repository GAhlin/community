package com.example.community.service.impl;

import com.example.community.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作Service的实现类
 * Created by macro on 2018/8/7.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }

    public void setHash(String key, String k, String v) {
        stringRedisTemplate.opsForHash().put(key, k, v);
    }

    public Set<Object> hashKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    public Object getHash(String key, String k) {
        return stringRedisTemplate.opsForHash().get(key,k);
    }

    public Long incrementHash(String key, String k, long delta) {
        return stringRedisTemplate.opsForHash().increment(key, k, delta);
    }

    public Boolean hasKey(String key, String k) {
        return stringRedisTemplate.opsForHash().hasKey(key, k);
    }

    public void delHash(String key, Object k){
        stringRedisTemplate.opsForHash().delete(key, k);
    }
}
