package com.course.config;

import com.course.config.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * Created by 84074 on 2017/11/9.
 */

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //按顺序执行拦截器。  .action不可缺少
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index")
                .excludePathPatterns("/toBorrowerLogin*")
                .excludePathPatterns("/borrowerSearch*")
                .excludePathPatterns("/login*")
                .excludePathPatterns("/toLogin*")
                .excludePathPatterns("/toError*");
        super.addInterceptors(registry);
    }
}
