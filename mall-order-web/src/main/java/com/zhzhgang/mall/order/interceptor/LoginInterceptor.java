package com.zhzhgang.mall.order.interceptor;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.utils.CookieUtils;
import com.zhzhgang.mall.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户是否登录的拦截器
 * @author zhzhgang
 * @create 2018-03-19 21:54
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private String tokenKey;

    @Value("${SSO_URL}")
    private String ssoUrl;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 执行 handler 之前先执行此方法
        // 返回 true：放行，返回 false：拦截

        // 1.从 cookie 中取 token 信息
        String token = CookieUtils.getCookieValue(httpServletRequest, tokenKey);

        // 2.如果取不到 token，跳转到 sso 登录页面，需要把当前请求的 URL 当做参数传递给 sso，sso 登录成功之后，跳转回请求的页面
        if (StringUtils.isBlank(token)) {
            String requestURL = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(ssoUrl + "/page/login?url=" + requestURL);
            return false;
        }
        // 3.如果取到 token，调用 sso 系统的服务，判断用户是否登录
        MallResult result = userService.getUserByToken(token);
        // 4.如果用户未登录，即没取到用户信息，跳转到 sso 登录页面
        if (result.getStatus() != 200) {
            String requestURL = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(ssoUrl + "/page/login?url=" + requestURL);
            return false;
        }
        // 5.如果用户登录，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // handler 执行之后，ModelAndView 返回之前，执行此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // ModelAndView 返回之后，执行此方法，多用于异常处理

    }
}
