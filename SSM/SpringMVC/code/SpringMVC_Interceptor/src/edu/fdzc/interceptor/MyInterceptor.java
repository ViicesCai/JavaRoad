package edu.fdzc.interceptor;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * 1.实现HandlerInterceptor接口
 * 2.在SpringMVC配置文件中注册这个拦截器的工作
 *      配置该拦截器拦截哪些请求的目标方法
 *
 * @author CAI
 * @time 2021/2/21
 */
public class MyInterceptor implements HandlerInterceptor {

    // 目标方法运行之前运行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle running...");

        return true;
    }

    // 目标方法运行之后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle running...");

    }

    // 在整个请求完成之后，到达目标页面之后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion running...");
    }
}
