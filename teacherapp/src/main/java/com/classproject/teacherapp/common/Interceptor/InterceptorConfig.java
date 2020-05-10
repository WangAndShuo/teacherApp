package com.classproject.teacherapp.common.Interceptor;

/**
 * @ClassName InterceptorConfig
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/26 13:42
 **/
import com.classproject.teacherapp.common.Interceptor.ParamInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Value("${portal.exclude.path}")
    private String excludePath;

    @Bean
    public ParamInterceptor getParamInterceptor() {
        return new ParamInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //此处配置拦截路径
        registry.addInterceptor(getParamInterceptor()).addPathPatterns("/**")
        .excludePathPatterns(excludePath.split(","));
    }

}
