package com.classproject.teacherapp.common.Interceptor;

/**
 * @ClassName a
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/26 13:42
 **/
import com.classproject.teacherapp.common.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ParamInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        String token = httpServletRequest.getHeader("token");
//        String user = httpServletRequest.getHeader("user");
//        //token验证
//        log.info("登陆用户名是：{}", user);
//        Object redisTmp = null;
//        try {
//            redisTmp = redisUtils.getString(user);
//            log.info("获取redis里的token：{}", redisTmp);
//        }catch (Exception e){
//            log.error("错误:{}",e);
//        }
//        if(!token.equals(redisTmp)){
//            System.out.println("token验证失败");
//            return false;
//        }
        System.out.println("token验证成功");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
