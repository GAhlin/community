package com.example.community.service;

import java.util.Map;
import java.util.Set;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 * Created by macro on 2018/8/7.
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

    void setHash(String key, String k, String v);

    Set<Object> hashKeys(String key);

    Object getHash(String key, String k);

    Long incrementHash(String key, String k, long delta);

    Boolean hasKey(String key, String k);

    void delHash(String key, Object k);
}
