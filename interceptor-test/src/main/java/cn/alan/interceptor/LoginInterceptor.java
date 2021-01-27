package cn.alan.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 实现HandlerInterceptor接口，可以有3个方法，分别拦截不同的位置
 * 1、实现拦截器（本类）
 * 2、将拦截器注册到实现了WebMvcConfigurer接口的配置类中，通过 addInterceptors() 方法
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前进行拦截处理
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 进行登陆检查
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        // 已经登陆，放行
        if(null != loginUser){
            return true;
        }
        // 未登陆，转到跳转页
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 目标方法执行之后进行拦截处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 页面渲染完成
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
