package cn.alan.config;

import cn.alan.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截所有请求路径
                .addPathPatterns("/**")
                // 要放行的请求路径,注意要放行所有的静态资源,如下例
                .excludePathPatterns("/","/login","/css/**","/js/**","/images/**");
    }
}
