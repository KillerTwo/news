package com.lwt.news.interceptor;



import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@SessionAttributes({"accountName","loginTime"})
public class AdminInterCeptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler
                            ) throws Exception {
        String accountName = (String) request.getSession()
                .getAttribute("accountName");
        if(accountName == null){
            //用户没有登陆，跳转到登陆页面
            String getRequestURL =request.getRequestURL().toString();
            System.out.println(getRequestURL);
            System.out.println("拦截请求");
            response.sendRedirect("/news/home#/login");
            return false;
        }
        return true;
    }
}
