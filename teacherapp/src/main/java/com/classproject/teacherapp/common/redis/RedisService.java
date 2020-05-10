package com.classproject.teacherapp.common.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/26 20:48
 **/

public interface RedisService {
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
     boolean set(final String key, Object value) ;

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
     boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit);
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
     void remove(final String... keys) ;


    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
     Object getString(String key);
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) ;

    /**
     * 删除对应的value
     *
     * @param key
     */
     void remove(final String key) ;

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
     boolean exists(final String key);
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
     Object get(final String key) ;

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) ;

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v);

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) ;

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) ;

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) ;

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1);
}
