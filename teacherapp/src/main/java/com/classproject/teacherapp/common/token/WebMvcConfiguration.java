//package com.classproject.teacherapp.common.token;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @ClassName WebMvcConfiguration
// * @Description:
// * @Author wangshuo[wang_shuo2@suixingpay.com]
// * @Date 2020/4/24 15:57
// **/
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//
//    private static final String[] excludePathPatterns  = {"/api/token/api_token"};
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        registry.addInterceptor(tokenInterceptor)
//                .addPathPatterns("/api/**")
//                .excludePathPatterns(excludePathPatterns);
//    }
//}
