package com.zhzhgang.mall.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhzhgang
 * @create 2018-01-28 16:50
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler,
                                         Exception e) {

        log.info("进入全局异常处理器");
        log.debug("handler 的类型：" + handler.getClass());
        // 控制台打印异常信息
        e.printStackTrace();

        // 异常信息写入日志文件
        log.error("系统异常", e);

        // 发邮件
        // 发短信
        // 展示错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统异常，请稍后重试");
        modelAndView.setViewName("error/exception");

        return modelAndView;
    }
}
