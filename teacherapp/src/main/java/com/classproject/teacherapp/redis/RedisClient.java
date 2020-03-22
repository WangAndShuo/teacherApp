///**
// * All rights Reserved, Designed By Suixingpay.
// *
// * @author: zhoumaowang<zhou_mw @ suixingpay.com>
// * @date: 2017/3/22 上午11:01
// * @Copyright: ©2017 Suixingpay. All rights reserved.
// * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
// */
//package com.tech.omp.easypay.redis;
//
//import com.suixingpay.takin.redis.IRedisOperater;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 封装架构包redis
// *
// * @Author: li_tu@suixingpay.com
// * @Date: 2019-11-23 17:44
// * @Version: 1.0
// */
//@Slf4j
//@Component
//public class RedisClient {
//
//    @Autowired
//    private IRedisOperater redisOperater;
//
//    /**
//     * 获取缓存值
//     *
//     * @param cacheKey
//     * @return
//     */
//    public String getCacheValue(String cacheKey) {
//        return redisOperater.get(cacheKey);
//    }
//
//
//    /**
//     *
//     * @param key
//     * @return
//     */
//    public boolean isExit(String key) {
//        return redisOperater.exists(key);
//    }
//    /**
//     * 设置缓存值
//     *
//     * @param key
//     * @param value
//     */
//    public void setCacheValue(String key, String value) {
//        redisOperater.set(key, value);
//    }
//
//    /**
//     * 设置缓存值并设置有效期
//     *
//     * @param key
//     * @param value
//     */
//    public void setCacheValueForTime(String key, String value, int time) {
//        redisOperater.setex(key, value, time);
//    }
//
//    /**
//     * 删除key值
//     *
//     * @param key
//     */
//    public void delCacheByKey(String key) {
//        redisOperater.delete(key);
//    }
//
//    /**
//     * 设置token的有效期
//     *
//     * @param key
//     * @return
//     */
//    public boolean setExpireTime(String key, Long timeout) {
//        return redisOperater.setnxLong(key, timeout, timeout.intValue());
//    }
//
//}
